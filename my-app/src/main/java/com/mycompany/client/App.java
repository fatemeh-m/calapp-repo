package com.mycompany.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args){

        ApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);
        ClientApp clientApp = (ClientApp) context.getBean("clientApp");
        clientApp.run();
    }
}
