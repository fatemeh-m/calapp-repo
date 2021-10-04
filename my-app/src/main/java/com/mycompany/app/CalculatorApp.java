package com.mycompany.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This program implement a calculator that takes a text file
 * containing mathematical expressions and solves the expressions
 */
@Component
public class CalculatorApp {
    private String pathname;
    private LinkedList<Expression> expressionList;
    private static final Logger logger = LoggerFactory.getLogger(CalculatorApp.class);
    @Autowired
    private Calculator calculator;

    public void run(){
        readFile();
        runCalculator();
        saveResult();
    }

    private void readFile(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your file path:");
            pathname = scanner.nextLine();
            expressionList = FileManager.readExpressions(pathname);

        } catch (FileNotFoundException e) {
            logger.error("The file was not found!", e);
            System.exit(0);
        } catch (InputMismatchException e) {
            logger.error("Invalid expression found!", e);
            System.exit(0);
        } catch (Exception e) {
            logger.error("Process failed!", e);
            System.exit(0);
        }
    }

    private void runCalculator(){
        logger.info("Calculating expressions started...");
        try {
            for (Expression e: expressionList)
                calculator.calculate(e);
        } catch (InputMismatchException e) {
            logger.error(e.getMessage(), e);
            System.exit(0);
        }

    }

    private void saveResult(){
        try {
            FileManager.writeResults(pathname, expressionList);
            logger.info("Results saved successfully!");

        } catch (IOException e) {
            logger.error("Updating results failed!", e);
        } catch (Exception e) {
            logger.error("Process failed!", e);
            System.exit(0);
        }
    }

}
