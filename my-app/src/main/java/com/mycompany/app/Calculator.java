package com.mycompany.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.InputMismatchException;

/**
 *This class implements a calculator which
 * supports four basic math operations
 */
@Component
public class Calculator {
    private HashMap<Character, Operation> operationMap;

    public Calculator(HashMap<Character, Operation> map){
        operationMap = map;

    }

    public void calculate(Expression expression) throws InputMismatchException {
        if (!operationMap.containsKey(expression.getOperator()))
            throw new InputMismatchException("Invalid operator found!");

        Operation operation = operationMap.get(expression.getOperator());
        double result = operation.calculateResult(expression.getOperand1(), expression.getOperand2());
        expression.setResult(result);
    }
}
