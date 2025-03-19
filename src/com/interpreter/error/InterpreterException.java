package com.interpreter.error;

public class InterpreterException extends java.lang.RuntimeException {
    private final int line;
    private final int position;

    public InterpreterException(String message, int line, int position) {
        super(message);
        this.line = line;
        this.position = position;
    }

    public int getLine() {
        return line;
    }

    public int getPosition() {
        return position;
    }
} 