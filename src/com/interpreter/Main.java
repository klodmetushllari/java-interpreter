package com.interpreter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length > 0 && (args[0].equals("-h") || args[0].equals("--help"))) {
                printHelp();
                return;
            }

            InputHandler inputHandler;
            if (args.length == 0 || (args.length == 1 && args[0].equals("-i"))) {
                System.out.println("Interactive mode activated. Type 'EXIT' to quit.");
                System.out.println("Type 'HELP' for a list of available commands.");
                inputHandler = new ConsoleInputHandler();
            } else if (args.length == 2 && args[0].equals("-f")) {
                System.out.println("File mode activated. Processing file: " + args[1]);
                inputHandler = new FileInputHandler(args[1]);
            } else {
                System.out.println("Usage: java -jar interpreter.jar [-i] | [-f filename] | [-h]");
                System.out.println("  -i: Interactive mode (default)");
                System.out.println("  -f filename: File input mode");
                System.out.println("  -h, --help: Show this help message");
                return;
            }

            Interpreter interpreter = new Interpreter();
            interpreter.processInput(inputHandler);
        } catch (Exception e) {
            System.err.println("Fatal error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void printHelp() {
        System.out.println("Java Interpreter - Command Guide");
        System.out.println("================================\n");
        
        System.out.println("Usage:");
        System.out.println("  java -jar interpreter.jar [-i] | [-f filename] | [-h]");
        System.out.println("\nOptions:");
        System.out.println("  -i           Interactive mode (default)");
        System.out.println("  -f filename  File input mode");
        System.out.println("  -h, --help   Show this help message\n");
        
        System.out.println("Available Commands:");
        System.out.println("  HELP         Show this help message");
        System.out.println("  EXIT         Exit the interpreter");
        System.out.println("  PRINT expr   Print the value of an expression");
        System.out.println("  ASSIGN var expr  Assign a value to a variable\n");
        
        System.out.println("Expressions:");
        System.out.println("  Numbers:     42, 3.14, -10");
        System.out.println("  Strings:     \"Hello\", 'World'");
        System.out.println("  Variables:   x, myVar, counter");
        System.out.println("  Operators:   +, -, *, /, ==, !=, <, >, <=, >=");
        System.out.println("  Functions:   sqrt(x), abs(x), min(x,y), max(x,y)\n");
        
        System.out.println("Examples:");
        System.out.println("  PRINT 2 + 3 * 4");
        System.out.println("  ASSIGN x 10");
        System.out.println("  PRINT sqrt(16)");
        System.out.println("  PRINT \"Hello, \" + name");
        System.out.println("  ASSIGN result min(5, 3)");
    }
}