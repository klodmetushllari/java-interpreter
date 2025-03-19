package com.interpreter.ast;



import com.interpreter.Main;
import com.interpreter.runtime.VariableManager;
import com.interpreter.error.RuntimeException;

import java.util.List;

public class CommandNode implements ASTNode {
    private final String commandName;
    private final List<ASTNode> arguments;
    private final int line;
    private final int position;

    public CommandNode(String commandName, List<ASTNode> arguments, int line, int position) {
        this.commandName = commandName;
        this.arguments = arguments;
        this.line = line;
        this.position = position;
    }

    @Override
    public Object evaluate(VariableManager variableManager) {
        switch (commandName) {
            case "PRINT":
                if (arguments.size() != 1) {
                    throw new RuntimeException("PRINT command requires exactly one argument", line, position);
                }
                Object result = arguments.get(0).evaluate(variableManager);
                System.out.println(result);
                return result;

            case "ASSIGN":
                if (arguments.size() != 2) {
                    throw new RuntimeException("ASSIGN command requires exactly two arguments", line, position);
                }
                if (!(arguments.get(0) instanceof VariableNode)) {
                    throw new RuntimeException("First argument to ASSIGN must be a variable", line, position);
                }
                VariableNode varNode = (VariableNode) arguments.get(0);
                Object value = arguments.get(1).evaluate(variableManager);
                variableManager.assign(varNode.getName(), value);
                return value;

            case "HELP":
                Main.printHelp();
                return null;

            default:
                throw new RuntimeException("Unknown command: " + commandName, line, position);
        }
    }
}