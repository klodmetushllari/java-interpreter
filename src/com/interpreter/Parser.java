package com.interpreter;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int position = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public ASTNode parse() {
        if (peekToken().getType() == Token.TokenType.EOL) {
            return null; // Empty line
        }
        
        Token token = peekToken();
        if (token.getType() == Token.TokenType.COMMAND) {
            return parseCommand();
        } else {
            return parseExpression();
        }
    }

    private CommandNode parseCommand() {
        Token commandToken = consumeToken(Token.TokenType.COMMAND);
        String commandName = commandToken.getValue();
        List<ASTNode> arguments = new ArrayList<>();
        
        if (commandName.equals("ASSIGN")) {
            // ASSIGN requires a variable name first
            if (peekToken().getType() != Token.TokenType.IDENTIFIER) {
                throw new SyntaxException("ASSIGN requires a variable name", peekToken().getLine(), peekToken().getPosition());
            }
            Token identifierToken = consumeToken(Token.TokenType.IDENTIFIER);
            arguments.add(new VariableNode(identifierToken.getValue(), identifierToken.getLine(), identifierToken.getPosition()));
            
            // Then an expression
            arguments.add(parseExpression());
        } else {
            // Other commands take expressions as arguments
            if (peekToken().getType() != Token.TokenType.EOL) {
                arguments.add(parseExpression());
            }
        }
        
        consumeToken(Token.TokenType.EOL);
        return new CommandNode(commandName, arguments, commandToken.getLine(), commandToken.getPosition());
    }

    private ASTNode parseExpression() {
        return parseAdditive();
    }

    private ASTNode parseAdditive() {
        ASTNode left = parseMultiplicative();
        
        while (position < tokens.size()) {
            Token token = peekToken();
            if (token.getType() == Token.TokenType.OPERATOR && 
                (token.getValue().equals("+") || token.getValue().equals("-"))) {
                token = consumeToken(Token.TokenType.OPERATOR);
                ASTNode right = parseMultiplicative();
                left = new OperationNode(left, token.getValue(), right, token.getLine(), token.getPosition());
            } else {
                break;
            }
        }
        
        return left;
    }

    private ASTNode parseMultiplicative() {
        ASTNode left = parsePrimary();
        
        while (position < tokens.size()) {
            Token token = peekToken();
            if (token.getType() == Token.TokenType.OPERATOR && 
                (token.getValue().equals("*") || token.getValue().equals("/"))) {
                token = consumeToken(Token.TokenType.OPERATOR);
                ASTNode right = parsePrimary();
                left = new OperationNode(left, token.getValue(), right, token.getLine(), token.getPosition());
            } else {
                break;
            }
        }
        
        return left;
    }

    private ASTNode parsePrimary() {
        Token token = peekToken();
        
        switch (token.getType()) {
            case IDENTIFIER:
                token = consumeToken(Token.TokenType.IDENTIFIER);
                // Check if it's a function call
                if (peekToken().getType() == Token.TokenType.LEFT_PAREN) {
                    consumeToken(Token.TokenType.LEFT_PAREN);
                    List<ASTNode> arguments = new ArrayList<>();
                    
                    if (peekToken().getType() != Token.TokenType.RIGHT_PAREN) {
                        arguments.add(parseExpression());
                        
                        while (peekToken().getType() == Token.TokenType.COMMA) {
                            consumeToken(Token.TokenType.COMMA);
                            arguments.add(parseExpression());
                        }
                    }
                    
                    consumeToken(Token.TokenType.RIGHT_PAREN);
                    return new FunctionNode(token.getValue(), arguments, token.getLine(), token.getPosition());
                } else {
                    return new VariableNode(token.getValue(), token.getLine(), token.getPosition());
                }
                
            case NUMBER:
                token = consumeToken(Token.TokenType.NUMBER);
                return new LiteralNode(Double.parseDouble(token.getValue()));
                
            case STRING:
                token = consumeToken(Token.TokenType.STRING);
                return new LiteralNode(token.getValue());
                
            case LEFT_PAREN:
                consumeToken(Token.TokenType.LEFT_PAREN);
                ASTNode node = parseExpression();
                consumeToken(Token.TokenType.RIGHT_PAREN);
                return node;
                
            default:
                throw new SyntaxException("Unexpected token: " + token.getType(), token.getLine(), token.getPosition());
        }
    }

    private Token peekToken() {
        if (position >= tokens.size()) {
            return tokens.get(tokens.size() - 1); // Return EOL or EOF
        }
        return tokens.get(position);
    }

    private Token consumeToken(Token.TokenType expectedType) {
        if (position >= tokens.size()) {
            throw new SyntaxException("Unexpected end of input", 
                                     tokens.get(tokens.size() - 1).getLine(), 
                                     tokens.get(tokens.size() - 1).getPosition());
        }
        
        Token token = tokens.get(position++);
        if (token.getType() != expectedType) {
            throw new SyntaxException("Expected " + expectedType + ", got " + token.getType(), 
                                     token.getLine(), token.getPosition());
        }
        
        return token;
    }
} 