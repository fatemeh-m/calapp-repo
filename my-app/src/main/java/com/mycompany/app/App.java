package com.mycompany.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
//        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        CalculatorApp calculatorApp = (CalculatorApp) context.getBean("calculatorApp");
//        calculatorApp.run();

        ApplicationContext context1 = new AnnotationConfigApplicationContext(AppConfig.class);
        CalculatorApp calculatorApp1 = (CalculatorApp) context1.getBean("calculatorApp");
        calculatorApp1.run();
    }
}

