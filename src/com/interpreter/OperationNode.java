package com.interpreter;

public class OperationNode implements ASTNode {
    private final ASTNode left;
    private final String operator;
    private final ASTNode right;
    private final int line;
    private final int position;

    public OperationNode(ASTNode left, String operator, ASTNode right, int line, int position) {
        this.left = left;
        this.operator = operator;
        this.right = right;
        this.line = line;
        this.position = position;
    }

    @Override
    public Object evaluate(VariableManager variableManager) {
        Object leftValue = left.evaluate(variableManager);
        Object rightValue = right.evaluate(variableManager);
        
        // Handle numeric operations
        if (leftValue instanceof Number && rightValue instanceof Number) {
            double leftDouble = ((Number) leftValue).doubleValue();
            double rightDouble = ((Number) rightValue).doubleValue();
            
            switch (operator) {
                case "+": return leftDouble + rightDouble;
                case "-": return leftDouble - rightDouble;
                case "*": return leftDouble * rightDouble;
                case "/": 
                    if (rightDouble == 0) {
                        throw new RuntimeException("Division by zero", line, position);
                    }
                    return leftDouble / rightDouble;
                case "==": return leftDouble == rightDouble;
                case "!=": return leftDouble != rightDouble;
                case "<": return leftDouble < rightDouble;
                case ">": return leftDouble > rightDouble;
                case "<=": return leftDouble <= rightDouble;
                case ">=": return leftDouble >= rightDouble;
                default:
                    throw new RuntimeException("Unknown operator: " + operator, line, position);
            }
        }
        
        // Handle string concatenation
        if (operator.equals("+") && (leftValue instanceof String || rightValue instanceof String)) {
            return String.valueOf(leftValue) + String.valueOf(rightValue);
        }
        
        // Handle string comparison
        if (leftValue instanceof String && rightValue instanceof String) {
            String leftString = (String) leftValue;
            String rightString = (String) rightValue;
            
            switch (operator) {
                case "==": return leftString.equals(rightString);
                case "!=": return !leftString.equals(rightString);
                default:
                    throw new RuntimeException("Operator " + operator + " not supported for strings", line, position);
            }
        }
        
        throw new RuntimeException("Unsupported operation: " + leftValue.getClass().getSimpleName() + 
                                 " " + operator + " " + rightValue.getClass().getSimpleName(), line, position);
    }
} 