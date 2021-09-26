package com.mycompany.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.reflections.Reflections;
import java.util.HashMap;
import java.util.InputMismatchException;

/**
 *This class implements a calculator which
 * supports four basic math operations
 */
@Component
public class Calculator {
    private HashMap<Character, Operation> operationMap;

    public Calculator(){
        operationMap = map;

        Reflections reflections = new Reflections("com.mycompany.operator");
        Set<Class<? extends Operation>> subTypes = reflections.getSubTypesOf(Operation.class);
        for (Class<? extends Operation> opClass: subTypes) {
            Operation op = opClass.newInstance();
            operationMap.put(op.getKey(), op);
        }
//        operationMap.put('+', new Addition());
//        operationMap.put('-', new Subtraction());
//        operationMap.put('*', new Multiplication());
//        operationMap.put('/', new Division());
    }

    public void calculate(Expression expression) throws InputMismatchException {
        if (!operationMap.containsKey(expression.getOperator()))
            throw new InputMismatchException("Invalid operator found!");

        Operation operation = operationMap.get(expression.getOperator());
        double result = operation.calculateResult(expression.getOperand1(), expression.getOperand2());
        expression.setResult(result);
    }
}
