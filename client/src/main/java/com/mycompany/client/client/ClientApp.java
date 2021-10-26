package com.mycompany.client.client;

import com.mycompany.app.Expression;
import com.mycompany.client.Exceptions.InvalidExpressionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

@Component
public class ClientApp {
    private final Connection connection;
    private final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(ClientApp.class);

    @Autowired
    public ClientApp(Connection connection) {
        this.connection = connection;
    }

    public void startApp() {
        logger.info("Connecting to the server...");
        try {
            connection.connect();
            logger.info("Connected to the server successfully");
            run();

        } catch (Exception e) {
            logger.error("Error connecting to server");
            System.out.println("Enter any key to try again or exit");
            if (!scanner.nextLine().equals("exit")) {
                startApp();
            }
        }
    }

    private void run() {
        while (connection.isConnected()) {
            try {
                System.out.println("enter your expression:");
                String input = scanner.nextLine();
                Expression expression = getExpression(input);
                expression = calculate(expression);

                if (expression.isCalculated()) {
                    System.out.println("Result: " + expression);
                } else {
                    System.out.println("Calculating expression failed!");
                }

            } catch (InvalidExpressionException e) {
                logger.warn(e.getMessage(), e);
            } catch (Exception e) {
                logger.error("Error calculating failed", e);
            }

            System.out.println("Enter any key to try again or exit:");
            if (scanner.nextLine().equals("exit")) {
                try {
                    connection.disconnect();
                    logger.info("Disconnecting connection");
                } catch (IOException e) {
                    logger.error("Error disconnecting connection failed");
                }
                break;
            }
        }
    }

    private Expression getExpression(String input) throws InvalidExpressionException {
        double operand1, operand2;
        String operator;
        Scanner scanner = new Scanner(input);

        try {
            operand1 = scanner.nextDouble();
            if ((operator = scanner.next()).length() != 1)
                throw new InvalidExpressionException();
            operand2 = scanner.nextDouble();

        } catch (Exception e) {
            throw new InvalidExpressionException();
        }
        return new Expression(operand1, operand2, operator.charAt(0));
    }

    public Expression calculate(Expression expression) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(connection.getInputStream());

        out.writeObject(expression);
        expression = (Expression) in.readObject();
        return expression;
    }
}
