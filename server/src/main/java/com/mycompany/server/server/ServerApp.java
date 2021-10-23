package com.mycompany.server.server;


import com.mycompany.server.math.Calculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Component
public class ServerApp {
    @Value("${server.port}")
    private int PORT;
    @Value("${server.maxSize}")
    private int MAX_SIZE;
    private final Calculator calculator;
    private static final Logger logger = LoggerFactory.getLogger(ServerApp.class);

    @Autowired
    public ServerApp(Calculator calculator) {
        this.calculator = calculator;
    }

    public void run() {
        calculator.init();
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_SIZE);

        try {
            ServerSocket server = new ServerSocket(PORT);
            logger.info("Starting server on port " + PORT);

            while(true) {
                logger.info("Waiting for client...");

                try {
                    Socket newSocket = server.accept();
                    executorService.submit(new ClientHandler(newSocket, calculator));
                    logger.info("New client connected successfully");

                } catch(IOException e) {
                    logger.error("Error accepting client", e);
                }
            }

        } catch (Exception e) {
            logger.error("Error starting server on port "+PORT , e);
        }
    }
}
