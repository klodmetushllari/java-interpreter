package com.interpreter;

public class SyntaxException extends InterpreterException {
    public SyntaxException(String message, int line, int position) {
        super(message, line, position);
    }
} 