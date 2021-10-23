package com.mycompany.client.client;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.*;
import java.net.Socket;

@Component
public class Connection {
    @Value("${server.address}")
    private String ADDRESS;
    @Value("${server.port}")
    private String PORT;
    private Socket socket;


    public void connect() throws IOException {
        socket = new Socket(ADDRESS, Integer.parseInt(PORT));
    }

    public void disconnect() throws IOException {
        socket.close();
    }

    public ObjectOutputStream getOutputStream() throws IOException {
        return new ObjectOutputStream(socket.getOutputStream());
    }

    public ObjectInputStream getInputStream() throws IOException {
        return new ObjectInputStream(socket.getInputStream());
    }

}
