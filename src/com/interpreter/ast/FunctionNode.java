package com.interpreter.ast;


import com.interpreter.runtime.VariableManager;
import com.interpreter.error.RuntimeException;

import java.util.ArrayList;
import java.util.List;

public class FunctionNode implements ASTNode {
    private final String name;
    private final List<ASTNode> arguments;
    private final int line;
    private final int position;

    public FunctionNode(String name, List<ASTNode> arguments, int line, int position) {
        this.name = name;
        this.arguments = arguments;
        this.line = line;
        this.position = position;
    }

    @Override
    public Object evaluate(VariableManager variableManager) {
        List<Object> evaluatedArgs = new ArrayList<>();
        for (ASTNode arg : arguments) {
            evaluatedArgs.add(arg.evaluate(variableManager));
        }

        switch (name.toLowerCase()) {
            case "sqrt":
                if (evaluatedArgs.size() != 1) {
                    throw new RuntimeException("sqrt() requires exactly one argument", line, position);
                }
                if (!(evaluatedArgs.get(0) instanceof Number)) {
                    throw new RuntimeException("sqrt() argument must be a number", line, position);
                }
                double value = ((Number) evaluatedArgs.get(0)).doubleValue();
                if (value < 0) {
                    throw new RuntimeException("sqrt() argument must be non-negative", line, position);
                }
                return Math.sqrt(value);

            case "abs":
                if (evaluatedArgs.size() != 1) {
                    throw new RuntimeException("abs() requires exactly one argument", line, position);
                }
                if (!(evaluatedArgs.get(0) instanceof Number)) {
                    throw new RuntimeException("abs() argument must be a number", line, position);
                }
                return Math.abs(((Number) evaluatedArgs.get(0)).doubleValue());

            case "min":
                if (evaluatedArgs.size() != 2) {
                    throw new RuntimeException("min() requires exactly two arguments", line, position);
                }
                if (!(evaluatedArgs.get(0) instanceof Number) || !(evaluatedArgs.get(1) instanceof Number)) {
                    throw new RuntimeException("min() arguments must be numbers", line, position);
                }
                return Math.min(
                        ((Number) evaluatedArgs.get(0)).doubleValue(),
                        ((Number) evaluatedArgs.get(1)).doubleValue()
                );

            case "max":
                if (evaluatedArgs.size() != 2) {
                    throw new RuntimeException("max() requires exactly two arguments", line, position);
                }
                if (!(evaluatedArgs.get(0) instanceof Number) || !(evaluatedArgs.get(1) instanceof Number)) {
                    throw new RuntimeException("max() arguments must be numbers", line, position);
                }
                return Math.max(
                        ((Number) evaluatedArgs.get(0)).doubleValue(),
                        ((Number) evaluatedArgs.get(1)).doubleValue()
                );

            default:
                throw new RuntimeException("Unknown function: " + name, line, position);
        }
    }
}