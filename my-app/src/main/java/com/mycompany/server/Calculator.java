package com.mycompany.server;


import com.mycompany.app.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mycompany.Operation;
import java.util.InputMismatchException;

/**
 *This class implements a calculator which
 * supports four basic math operations
 */

@Component
public class Calculator {

    private OperatorsProvider operatorsProvider;

    @Autowired
    public Calculator(OperatorsProvider op) {
        operatorsProvider = op;
    }
    
    public void calculate(Expression expression) throws InputMismatchException {
        Operation operation = operatorsProvider.getOperator(expression.getOperator());
        double result = operation.calculateResult(expression.getOperand1(), expression.getOperand2());
        expression.setResult(result);

    }
}
