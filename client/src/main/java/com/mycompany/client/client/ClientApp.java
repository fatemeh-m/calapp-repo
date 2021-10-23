package com.mycompany.client.client;

import com.mycompany.app.Expression;
import com.mycompany.client.Exceptions.InvalidExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;


@Component
public class ClientApp {
    private final SendExpression sendExpression;
    private final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(ClientApp.class);

    @Autowired
    public ClientApp(SendExpression sendExpression){
        this.sendExpression = sendExpression;
    }

    public void startApp(){
        logger.info("Start calculator app");

        while (!scanner.nextLine().equals("exit")){
            if (sendExpression.connect()){
                System.out.println("you are connected");
                run();
                sendExpression.disconnect();
            } else {
                System.out.println("Connecting to the server failed.\npress any key to try again or exit");
            }
        }
    }

    private void run(){
        try {
            System.out.println("enter your expression:");
            String input = scanner.nextLine();
            Expression expression = getExpression(input);
            expression = sendExpression.send(expression);

            if (expression.isCalculated()) {
                System.out.println("Result: " + expression);
            } else {
                System.out.println("Calculating expression failed!");
            }

        } catch (InvalidExpression e){
            logger.error(e.getMessage(), e);

        } catch (Exception e){
            System.out.println("Process failed!");
            logger.error(e.getMessage(), e);
        }
    }

    private Expression getExpression(String input) throws InvalidExpression {
        double operand1, operand2;
        String operator;
        Scanner scanner = new Scanner(input);

        try {
            operand1 = scanner.nextDouble();
            if ((operator = scanner.next()).length() != 1)
                throw new InvalidExpression();
            operand2 = scanner.nextDouble();

        } catch (Exception e){
            throw new InvalidExpression();
        }
        return new Expression(operand1, operand2, operator.charAt(0));
    }
}
