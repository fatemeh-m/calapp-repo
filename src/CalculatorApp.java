import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This program implement a calculator that takes a text file
 * containing mathematical expressions and solves the expressions
 */
public class CalculatorApp {
    private String pathname;
    private LinkedList<Expression> expressionList;
    private Calculator calculator;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CalculatorApp(){
        calculator = new Calculator();
    }
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
            //System.out.println("The file was not found!");
            logger.error("The file was not found!");
            System.exit(0);
        } catch (InputMismatchException e) {
            logger.error("Invalid expression found!");
            //System.out.println("Invalid expression found!");
            System.exit(0);
        } catch (Exception e) {
            logger.error("Process failed!");
            System.exit(0);
        }
    }

    private void runCalculator(){
        System.out.println("Calculating expressions started...");
        try {
           expressionList.forEach(calculator::calculate);
        } catch (InputMismatchException e) {
            logger.error("Invalid operator found!");
         //   System.out.println("Invalid operator found!");
            System.exit(0);
        }

    }

    private void saveResult(){
        try {
            FileManager.writeResults(pathname, expressionList);
            System.out.println("Results saved successfully!");

        } catch (IOException e) {
            logger.error("Updating results failed!");
         //   System.out.println("Updating results failed!");
        } catch (Exception e) {
            logger.error("Process failed!");
            System.exit(0);
        }
    }
}
