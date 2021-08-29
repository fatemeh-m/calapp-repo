import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * This program implement a calculator that takes a text file
 * containing mathematical expressions and solves the expressions
 */
public class CalculatorApp {
    private static LinkedList<Expression> expressionList;
    private static Calculator calculator = new Calculator();

    public static void main(String[] args){
        readFile();
        runCalculator();
        saveResult();
    }

    private static void readFile(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your file path:");
        String path = scanner.nextLine();

        try {
            expressionList = FileManager.readExpressions(path);
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found!");
            System.exit(0);
        } catch (InputMismatchException e) {
            System.out.println("Invalid expression found!");
            System.exit(0);
        }
    }

    private static void runCalculator(){
        System.out.println("Calculating expressions started...");
        try {
           expressionList.forEach(calculator::calculate);
        } catch (InputMismatchException e) {
            System.out.println("Invalid operator found!");
            System.exit(0);
        }

    }

    private static void saveResult(){
        try {
            FileManager.writeResults(expressionList);
            System.out.println("Results saved successfully!");

        } catch (IOException e) {
            System.out.println("Updating results failed!");
        }
    }
}