package com.interpreter;

public class LexicalException extends InterpreterException {
    public LexicalException(String message, int line, int position) {
        super(message, line, position);
    }
} 