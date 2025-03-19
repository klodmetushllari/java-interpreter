package com.interpreter;

public class Token {
    private final TokenType type;
    private final String value;
    private final int line;
    private final int position;

    public Token(TokenType type, String value, int line, int position) {
        this.type = type;
        this.value = value;
        this.line = line;
        this.position = position;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public int getLine() {
        return line;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return String.format("Token(%s, '%s', line=%d, pos=%d)", type, value, line, position);
    }

    public enum TokenType {
        COMMAND,
        IDENTIFIER,
        NUMBER,
        STRING,
        OPERATOR,
        LEFT_PAREN,
        RIGHT_PAREN,
        COMMA,
        EOL,
        EOF
    }
} 