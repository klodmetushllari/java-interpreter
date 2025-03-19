package com.interpreter;

public class RuntimeException extends InterpreterException {
    public RuntimeException(String message, int line, int position) {
        super(message, line, position);
    }
} 