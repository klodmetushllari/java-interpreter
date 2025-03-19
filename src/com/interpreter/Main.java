package com.interpreter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            InputHandler inputHandler;
            if (args.length == 0 || (args.length == 1 && args[0].equals("-i"))) {
                System.out.println("Interactive mode activated. Type 'EXIT' to quit.");
                inputHandler = new ConsoleInputHandler();
            } else if (args.length == 2 && args[0].equals("-f")) {
                System.out.println("File mode activated. Processing file: " + args[1]);
                inputHandler = new FileInputHandler(args[1]);
            } else {
                System.out.println("Usage: java -jar interpreter.jar [-i] | [-f filename]");
                System.out.println("  -i: Interactive mode (default)");
                System.out.println("  -f filename: File input mode");
                return;
            }

            Interpreter interpreter = new Interpreter();
            interpreter.processInput(inputHandler);
        } catch (Exception e) {
            System.err.println("Fatal error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}