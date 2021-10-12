package com.mycompany.server;

import com.mycompany.app.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class ServerApp {
    private final int PORT = 5000;
    private ServerSocket server;
    private Calculator calculator;
    private static final Logger logger = LoggerFactory.getLogger(ServerApp.class);

    @Autowired
    public ServerApp(Calculator calculator){
        this.calculator = calculator;
        try {
            server = new ServerSocket(PORT);
        } catch (IOException e) {
            logger.error("Unable to create server", e);
        }
    }

    public void run() {
        while (true) {
            try {
                Socket client;
                client = server.accept();

                ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
                ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());

//                while (!client.isClosed()) {
                    Expression expression = (Expression) inputStream.readObject();
                    calculator.calculate(expression);
                    outputStream.writeObject(expression);
//                }

            } catch (IOException | ClassNotFoundException e) {
                logger.error("Process failed!", e);
            }
        }
    }
}
