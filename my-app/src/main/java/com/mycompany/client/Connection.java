package com.mycompany.client;

import com.mycompany.app.Expression;
import org.springframework.stereotype.Component;
import java.io.*;
import java.net.Socket;

@Component
public class Connection {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    
    private final String ADDRESS = "127.0.0.1";
    private final int PORT = 5000;


    public Expression execute(Expression expression) throws IOException, ClassNotFoundException {
        connect();
        outputStream.writeObject(expression);
        expression = (Expression) inputStream.readObject();
        socket.close();

        return expression;
    }

    private void connect() throws IOException {
        socket = new Socket(ADDRESS, PORT);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }
}
