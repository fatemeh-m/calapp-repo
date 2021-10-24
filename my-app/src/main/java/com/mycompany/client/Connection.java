package com.mycompany.client;

import com.mycompany.app.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.*;
import java.net.Socket;

@Component
public class Connection {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private static final Logger logger = LoggerFactory.getLogger(Connection.class);


    public Connection(){
        try {
            socket = new Socket("127.0.0.1", 5000);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            logger.error("connecting to server failed!", e);
        }
    }

    public Expression execute(Expression expression) throws IOException, ClassNotFoundException {
        outputStream.writeObject(expression);
        expression = (Expression) inputStream.readObject();

        return expression;
    }
    public void close() throws IOException {
        socket.close();
    }
}
