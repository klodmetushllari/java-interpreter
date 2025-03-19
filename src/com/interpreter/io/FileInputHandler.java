package com.interpreter.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileInputHandler implements InputHandler {
    private final BufferedReader reader;
    private int lineNumber = 0;
    private String cachedLine = null;

    public FileInputHandler(String filename) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
    }

    @Override
    public String readLine() throws IOException {
        String line;
        if (cachedLine != null) {
            line = cachedLine;
            cachedLine = null;
        } else {
            line = reader.readLine();
            lineNumber++;
        }
        return line;
    }

    @Override
    public boolean hasMoreInput() throws IOException {
        if (cachedLine != null) return true;

        cachedLine = reader.readLine();
        if (cachedLine == null) return false;

        lineNumber++;
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