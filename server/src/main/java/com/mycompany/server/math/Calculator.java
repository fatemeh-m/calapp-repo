package com.mycompany.server.math;


import com.mycompany.app.Expression;
import com.mycompany.server.exceptions.InvalidOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mycompany.Operation;

/**
 *This class implements a calculator which
 * supports four basic com.mycompany.server.math operations
 */

@Component
public class Calculator {
    private final OperatorsProvider operatorsProvider;
    private static final Logger logger = LoggerFactory.getLogger(Calculator.class);

    @Autowired
    public Calculator(OperatorsProvider operatorsProvider) {
        this.operatorsProvider = operatorsProvider;
    }
    
    public void calculate(Expression expression) {
        try {
            Operation operation = operatorsProvider.getOperator(expression.getOperator());
            double result = operation.calculateResult(expression.getOperand1(), expression.getOperand2());
            expression.setResult(result);
            expression.setCalculated();

        } catch (InvalidOperator invalidOperator) {
            logger.error(invalidOperator.getMessage());
        }
    }

    public void init() {
        operatorsProvider.addSupportedOperators();
    }
}
