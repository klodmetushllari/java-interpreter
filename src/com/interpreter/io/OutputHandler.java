package com.interpreter.io;

public class OutputHandler {
    private boolean verbose = false;

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public void output(String message) {
        System.out.println(message);
    }

    public void debug(String message) {
        if (verbose) {
            System.out.println("[DEBUG] " + message);
        }
    }
}