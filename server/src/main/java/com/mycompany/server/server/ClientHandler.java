package com.mycompany.server.server;

import com.mycompany.server.math.Calculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import com.mycompany.app.Expression;

public class ClientHandler implements Runnable {
    private final Calculator calculator;
    private final Socket client;
    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);

    public ClientHandler(Socket client, Calculator calculator) {
        this.client = client;
        this.calculator = calculator;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inputStream;
            ObjectOutputStream outputStream;
            Expression expression;

            while (client.isConnected()) {
                logger.info("Ready to calculating expressions...");
                try {
                    inputStream = new ObjectInputStream(client.getInputStream());
                    outputStream = new ObjectOutputStream(client.getOutputStream());

                    if ((expression = (Expression) inputStream.readObject()) == null) {
                        continue;
                    }
                    calculator.calculate(expression);
                    outputStream.writeObject(expression);
                    logger.info("Expression " + expression + (expression.isCalculated() ? " calculated successfully!" : " failed to calculate!"));
                } catch (ClassNotFoundException e) {
                    logger.error("Error reading expression", e);
                }
            }
        } catch (IOException e) {
            logger.error("Error servicing to the client", e);
        }
        logger.info("Client disconnected");
    }
}
