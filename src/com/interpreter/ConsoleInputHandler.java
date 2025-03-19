package com.interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputHandler implements InputHandler {
    private final BufferedReader reader;
    private int lineNumber = 0;
    private boolean hasMoreInput = true;
    private String cachedLine = null;

    public ConsoleInputHandler() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String readLine() throws IOException {
        String line;
        if (cachedLine != null) {
            line = cachedLine;
            cachedLine = null;
        } else {
            System.out.print("> ");
            line = reader.readLine();
            lineNumber++;
            
            if (line == null || line.equalsIgnoreCase("EXIT")) {
                hasMoreInput = false;
                return null;
            }
        }
        return line;
    }

    @Override
    public boolean hasMoreInput() throws IOException {
        if (!hasMoreInput) return false;
        
        if (cachedLine == null) {
            System.out.print("> ");
            cachedLine = reader.readLine();
            lineNumber++;
            
            if (cachedLine == null || cachedLine.equalsIgnoreCase("EXIT")) {
                hasMoreInput = false;
                cachedLine = null;
                return false;
            }
        }
        return true;
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
} 