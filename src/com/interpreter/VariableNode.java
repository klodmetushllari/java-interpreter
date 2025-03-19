package com.interpreter;

public class VariableNode implements ASTNode {
    private final String name;
    private final int line;
    private final int position;

    public VariableNode(String name, int line, int position) {
        this.name = name;
        this.line = line;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    @Override
    public Object evaluate(VariableManager variableManager) {
        if (!variableManager.exists(name)) {
            throw new RuntimeException("Undefined variable: " + name, line, position);
        }
        return variableManager.get(name);
    }
} 