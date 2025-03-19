package com.interpreter;

import java.util.HashMap;
import java.util.Map;

public class VariableManager {
    private final Map<String, Object> variables = new HashMap<>();

    public void assign(String name, Object value) {
        variables.put(name, value);
    }

    public Object get(String name) {
        return variables.get(name);
    }

    public boolean exists(String name) {
        return variables.containsKey(name);
    }
} 