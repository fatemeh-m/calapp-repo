package com.mycompany.app;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
//import com.mycompany.operators.*;
import com.mycompany.Operation;
/**
 *This class implements a calculator which
 * supports four basic math operations
 */
@Component
public class Calculator {

    private HashMap<Character, Operation> operationMap;
    private static final Logger logger = LoggerFactory.getLogger(Calculator.class);

    public Calculator(){
        operationMap = new HashMap<>();

        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("config.properties not found!", e);
            System.exit(0);
        }
        Reflections reflections = new Reflections(properties.getProperty("operators.location"));
        Set<Class<? extends Operation>> classes = reflections.getSubTypesOf(Operation.class);

        for (Class<? extends Operation> opClass : classes) {
            Operation op = null;
            try {
                op = opClass.newInstance();
            } catch (InstantiationException e) {
                logger.error("operator " + opClass.getName() + "didn't instantiate!", e);
            } catch (IllegalAccessException e) {
                logger.error("operator " + opClass.getName() + "didn't instantiate!", e);
            }
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
