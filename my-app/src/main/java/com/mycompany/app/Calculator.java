package com.mycompany.app;

import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Set;

/**
 *This class implements a calculator which
 * supports four basic math operations
 */
@Component
public class Calculator {
    private HashMap<Character, Operation> operationMap;

    public Calculator() throws IllegalAccessException, InstantiationException {
        operationMap = new HashMap<>();

        Reflections reflections = new Reflections("com.mycompany.operators");
        Set<Class<? extends Operation>> classes = reflections.getSubTypesOf(Operation.class);

        for (Class<? extends Operation> opClass : classes) {
            Operation op = opClass.newInstance();
            operationMap.put(op.getKey(), op);
        }
    }

    public void calculate(Expression expression) throws InputMismatchException {
        if (!operationMap.containsKey(expression.getOperator())){
            throw new InputMismatchException("Invalid operator found!");
        }

        Operation operation = operationMap.get(expression.getOperator());
        double result = operation.calculateResult(expression.getOperand1(), expression.getOperand2());
        expression.setResult(result);
    }
}
