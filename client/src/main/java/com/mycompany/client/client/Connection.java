package com.mycompany.client.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.*;
import java.net.Socket;

@Component
public class Connection {
    @Value("${server.address}")
    private String address;
    @Value("${server.port}")
    private String port;
    private Socket socket;

    public void connect() throws IOException {
        socket = new Socket(address, Integer.parseInt(port));
    }

    public void disconnect() throws IOException {
        socket.close();
    }

    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }

    public Boolean isConnected() {
        return socket.isConnected();
    }
}
