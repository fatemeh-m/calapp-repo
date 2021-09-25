package com.mycompany.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;


@Configuration
public class AppConfig {
    @Bean
    public Calculator calculator(){
        return new Calculator(operationMap());
    }
    @Bean
    public CalculatorApp calculatorApp(){
        return new CalculatorApp();
    }
    @Bean
    public Subtraction subtraction(){
        return new Subtraction();
    }
    @Bean
    public Addition addition(){
        return new Addition();
    }
    @Bean
    public Multiplication multiplication(){
        return new Multiplication();
    }
    @Bean
    public Division division(){
        return new Division();
    }
    @Bean
    public HashMap<Character, Operation> operationMap(){
        HashMap<Character, Operation> map = new HashMap<>();
        map.put('-', subtraction());
        map.put('+', addition());
        map.put('*', multiplication());
        map.put('/', division());
        return map;
    }
    @Bean
    public Logger logger(){
        return LoggerFactory.getLogger(calculatorApp().getClass());
    }
}
