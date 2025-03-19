package com.interpreter.lexer;

import com.interpreter.error.LexicalException;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String input;
    private final int line;
    private int position = 0;

    public Lexer(String input, int line) {
        this.input = input;
        this.line = line;
    }

    public List<Token> tokenize() throws LexicalException {
        List<Token> tokens = new ArrayList<>();

        while (position < input.length()) {
            char c = input.charAt(position);

            if (Character.isWhitespace(c)) {
                position++;
            } else if (Character.isLetter(c)) {
                tokens.add(lexIdentifierOrCommand());
            } else if (Character.isDigit(c)) {
                tokens.add(lexNumber());
            } else if (c == '"' || c == '\'') {
                tokens.add(lexString());
            } else if (isOperator(c)) {
                tokens.add(lexOperator());
            } else if (c == '(') {
                tokens.add(new Token(Token.TokenType.LEFT_PAREN, "(", line, position++));
            } else if (c == ')') {
                tokens.add(new Token(Token.TokenType.RIGHT_PAREN, ")", line, position++));
            } else if (c == ',') {
                tokens.add(new Token(Token.TokenType.COMMA, ",", line, position++));
            } else {
                throw new LexicalException("Invalid character: " + c, line, position);
            }
        }

        tokens.add(new Token(Token.TokenType.EOL, "EOL", line, position));
        return tokens;
    }

    private Token lexIdentifierOrCommand() {
        int startPos = position;
        StringBuilder sb = new StringBuilder();

        while (position < input.length() &&
                (Character.isLetterOrDigit(input.charAt(position)) || input.charAt(position) == '_')) {
            sb.append(input.charAt(position++));
        }

        String value = sb.toString();

        // Check if it's a command (all uppercase)
        boolean isCommand = true;
        for (char c : value.toCharArray()) {
            if (Character.isLetter(c) && !Character.isUpperCase(c)) {
                isCommand = false;
                break;
            }
        }

        if (isCommand && value.equals(value.toUpperCase())) {
            return new Token(Token.TokenType.COMMAND, value, line, startPos);
        } else {
            return new Token(Token.TokenType.IDENTIFIER, value, line, startPos);
        }
    }

    private Token lexNumber() {
        int startPos = position;
        StringBuilder sb = new StringBuilder();

        while (position < input.length() && Character.isDigit(input.charAt(position))) {
            sb.append(input.charAt(position++));
        }

        // Check for decimal point
        if (position < input.length() && input.charAt(position) == '.') {
            sb.append(input.charAt(position++));

            // Must have at least one digit after decimal point
            if (position < input.length() && Character.isDigit(input.charAt(position))) {
                while (position < input.length() && Character.isDigit(input.charAt(position))) {
                    sb.append(input.charAt(position++));
                }
            } else {
                throw new LexicalException("Invalid number format: missing digits after decimal point", line, position);
            }
        }

        return new Token(Token.TokenType.NUMBER, sb.toString(), line, startPos);
    }

    private Token lexString() {
        int startPos = position;
        char quote = input.charAt(position++); // Save the quote character and move past it
        StringBuilder sb = new StringBuilder();

        while (position < input.length() && input.charAt(position) != quote) {
            sb.append(input.charAt(position++));
        }

        if (position >= input.length()) {
            throw new LexicalException("Unterminated string", line, startPos);
        }

        // Move past the closing quote
        position++;

        return new Token(Token.TokenType.STRING, sb.toString(), line, startPos);
    }

    private Token lexOperator() {
        int startPos = position;
        char c = input.charAt(position++);

        if (c == '+' || c == '-' || c == '*' || c == '/') {
            return new Token(Token.TokenType.OPERATOR, String.valueOf(c), line, startPos);
        }

        // Check for two-character operators
        if (position < input.length()) {
            String op = c + String.valueOf(input.charAt(position));
            if (op.equals("==") || op.equals("!=") || op.equals("<=") || op.equals(">=")) {
                position++;
                return new Token(Token.TokenType.OPERATOR, op, line, startPos);
            }
        }

        return new Token(Token.TokenType.OPERATOR, String.valueOf(c), line, startPos);
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '=' || c == '!' || c == '<' || c == '>';
    }
}