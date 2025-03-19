package com.interpreter.io;

import java.io.IOException;

public interface InputHandler {
    String readLine() throws IOException;
    boolean hasMoreInput() throws IOException;
    int getLineNumber();
    void close() throws IOException;
}