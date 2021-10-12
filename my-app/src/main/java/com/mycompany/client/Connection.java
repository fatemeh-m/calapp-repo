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


    public Expression execute(Expression expression) throws IOException, ClassNotFoundException {
        socket = new Socket("127.0.0.1", 5000);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());

        outputStream.writeObject(expression);
        expression = (Expression) inputStream.readObject();
        return expression;
    }
}
