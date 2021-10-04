package com.mycompany.app;

import com.mycompany.Operation;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Set;

@Component
public class OperatorsProvider {

    private final HashMap<Character, Operation> operatorsMap = new HashMap<Character, Operation>();
    private static final Logger logger = LoggerFactory.getLogger(OperatorsProvider.class);
    PropertyLoader propertyLoader;

    @Autowired
    public OperatorsProvider(PropertyLoader pl) {
        propertyLoader = pl;
        addSupportedOperators();
    }

    private void addSupportedOperators(){
        Reflections reflections = new Reflections(propertyLoader.getPath());
        Set<Class<? extends Operation>> classes = reflections.getSubTypesOf(Operation.class);

        Operation op = null;
        for (Class<? extends Operation> opClass : classes) {
            try {
                op = opClass.newInstance();
                operatorsMap.put(op.getKey(), op);

            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("operator " + opClass.getName() + "didn't instantiate!", e);
            }
        }
    }

    public Operation getOperator(char key) {
        if (!operatorsMap.containsKey(key)){
            throw new InputMismatchException("Invalid operator found!");
        }
        return operatorsMap.get(key);
    }

}