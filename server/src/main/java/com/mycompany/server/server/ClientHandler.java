package com.mycompany.server.server;

import com.mycompany.server.math.Calculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import com.mycompany.app.Expression;


public class ClientHandler implements Runnable{
    private final Calculator calculator;
    private final Socket client;
    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);

    public ClientHandler(Socket client, Calculator calculator){
        this.client = client;
        this.calculator = calculator;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
            logger.info("Ready to calculating expressions...");

            Expression expression = (Expression) inputStream.readObject();
            calculator.calculate(expression);
            outputStream.writeObject(expression);
            logger.info("Expression " + expression + " calculated successfully!");

        } catch (Exception e) {
            logger.error("Error servicing to the client", e);
        }

    }
}
