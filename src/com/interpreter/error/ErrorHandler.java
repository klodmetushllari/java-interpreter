package com.interpreter.error;


public class ErrorHandler {
    public void handleError(InterpreterException e, String line) {
        StringBuilder errorMessage = new StringBuilder();

        // Add error type and location
        String errorType = getErrorType(e);
        errorMessage.append(errorType)
                .append(" at line ")
                .append(e.getLine())
                .append(", position ")
                .append(e.getPosition())
                .append(":\n  ")
                .append(line)
                .append("\n  ");

        // Add pointer to the error position
        for (int i = 0; i < e.getPosition(); i++) {
            errorMessage.append(" ");
        }
        errorMessage.append("^\n");

        // Add error message
        errorMessage.append(e.getMessage());

        // Print the formatted error message
        System.err.println(errorMessage.toString());
    }

    private String getErrorType(InterpreterException e) {
        if (e instanceof LexicalException) {
            return "LEXICAL ERROR";
        } else if (e instanceof SyntaxException) {
            return "SYNTAX ERROR";
        } else if (e instanceof RuntimeException) {
            return "RUNTIME ERROR";
        } else {
            return "ERROR";
        }
    }
}