package com.mycompany.client;

import com.mycompany.client.client.ClientApp;
import com.mycompany.client.config.ClientConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);
        ClientApp clientApp = (ClientApp) context.getBean("clientApp");
        clientApp.startApp();
    }
}
