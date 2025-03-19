package com.interpreter.core;

import com.interpreter.ast.ASTNode;
import com.interpreter.error.ErrorHandler;
import com.interpreter.error.InterpreterException;
import com.interpreter.io.ConsoleInputHandler;
import com.interpreter.io.InputHandler;
import com.interpreter.io.OutputHandler;
import com.interpreter.lexer.Lexer;
import com.interpreter.lexer.Token;
import com.interpreter.parser.Parser;
import com.interpreter.runtime.VariableManager;

import java.io.IOException;
import java.util.List;

public class Interpreter {
    private final VariableManager variableManager;
    private final OutputHandler outputHandler;
    private final ErrorHandler errorHandler;

    public Interpreter() {
        this.variableManager = new VariableManager();
        this.outputHandler = new OutputHandler();
        this.errorHandler = new ErrorHandler();
    }

    public void processInput(InputHandler inputHandler) {
        try {
            while (inputHandler.hasMoreInput()) {
                String line = inputHandler.readLine();
                if (line == null || line.trim().isEmpty()) {
                    continue;
                }

                try {
                    // Lexical analysis
                    Lexer lexer = new Lexer(line, inputHandler.getLineNumber());
                    List<Token> tokens = lexer.tokenize();

                    // Parsing
                    Parser parser = new Parser(tokens);
                    ASTNode ast = parser.parse();

                    // Execution
                    if (ast != null) {
                        ast.evaluate(variableManager);
                    }
                } catch (InterpreterException e) {
                    errorHandler.handleError(e, line);
                    if (!(inputHandler instanceof ConsoleInputHandler)) {
                        // In file mode, we stop on the first error
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        } finally {
            try {
                inputHandler.close();
            } catch (IOException e) {
                System.err.println("Error closing input: " + e.getMessage());
            }
        }
    }
}