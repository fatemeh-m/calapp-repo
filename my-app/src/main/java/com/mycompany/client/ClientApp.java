package com.mycompany.client;

import com.mycompany.app.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class ClientApp {
    private final Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(ClientApp.class);

    @Autowired
    public ClientApp(Connection connection){
        this.connection = connection;
    }

    public void run(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter your expression:");
            String input = scanner.nextLine();

            while (!input.equals("Exit")){
                try {
                    Expression expression = getExpression(input);
                    expression = connection.execute(expression);
                    System.out.println(expression.toString());

                } catch (InputMismatchException e){
                    System.out.println("Invalid expression found!");
                } catch (Exception e){
                    System.out.println("Process failed!");
                    logger.error(e.getMessage(), e);
                }
                System.out.println("enter your expression:");
                input = scanner.nextLine();
            }
            connection.close();
        } catch (Exception e){
            System.out.println("Process failed!");
            logger.error(e.getMessage(), e);
        }
    }

    public Expression getExpression(String input) throws InputMismatchException{
        double op1, op2;
        String operator;
        Scanner scanner = new Scanner(input);

        op1 = scanner.nextDouble();
        if ((operator = scanner.next()).length() != 1)
            throw new InputMismatchException();
        op2 = scanner.nextDouble();

        return new Expression(op1, op2, operator.charAt(0));
    }
}
