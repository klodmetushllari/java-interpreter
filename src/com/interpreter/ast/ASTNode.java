package com.interpreter.ast;

import com.interpreter.runtime.VariableManager;

public interface ASTNode {
    Object evaluate(VariableManager variableManager);
} 