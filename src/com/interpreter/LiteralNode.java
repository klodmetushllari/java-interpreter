package com.interpreter;

public class LiteralNode implements ASTNode {
    private final Object value;

    public LiteralNode(Object value) {
        this.value = value;
    }

    @Override
    public Object evaluate(VariableManager variableManager) {
        return value;
    }
} 