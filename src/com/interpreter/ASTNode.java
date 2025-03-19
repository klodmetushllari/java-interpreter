package com.interpreter;

public interface ASTNode {
    Object evaluate(VariableManager variableManager);
} 