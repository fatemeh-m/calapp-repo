package com.mycompany.client.client;

import com.mycompany.app.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Component
public class SendExpression {
    private final Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(SendExpression.class);

    @Autowired
    public SendExpression(Connection connection){
        this.connection = connection;
    }

    public Expression send(Expression expression){
        try {
            ObjectOutputStream outputStream = connection.getOutputStream();
            ObjectInputStream inputStream = connection.getInputStream();

            outputStream.writeObject(expression);
            expression = (Expression) inputStream.readObject();

        } catch (Exception e) {
            logger.error("Error sending expression", e);
        }
        return expression;
    }

    public boolean connect(){
        try {
            connection.connect();
            logger.info("Connected to the server successfully");

        } catch (IOException e) {
            logger.error("Error connecting to the server", e);
            return false;
        }
        return true;
    }

    public void disconnect(){
        try {
            connection.disconnect();
            logger.info("Disconnecting connection");
        } catch (IOException e) {
            logger.error("Error disconnecting connection", e);
        }
    }
}
