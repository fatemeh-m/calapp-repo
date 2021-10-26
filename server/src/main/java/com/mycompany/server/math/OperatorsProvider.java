package com.mycompany.server.math;

import com.mycompany.Operation;
import com.mycompany.server.exceptions.InvalidOperatorException;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Set;

@Component
public class OperatorsProvider {
    @Value("${operators.packageName}")
    private String packageName;
    private HashMap<Character, Operation> operatorsMap;
    private static final Logger logger = LoggerFactory.getLogger(OperatorsProvider.class);

    public void addSupportedOperators(){
        operatorsMap = new HashMap<>();
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends Operation>> classes = reflections.getSubTypesOf(Operation.class);
        Operation operation;

        for (Class<? extends Operation> opClass : classes) {

            try {
                operation = opClass.getDeclaredConstructor().newInstance();
                operatorsMap.put(operation.getKey(), operation);

            } catch (Exception e) {
                logger.error("operator " + opClass.getName() + "didn't instantiate!", e);
            }
        }
    }

    public Operation getOperator(char key) throws InvalidOperatorException {
        if (!operatorsMap.containsKey(key)){
            throw new InvalidOperatorException();
        }
        return operatorsMap.get(key);
    }
}
