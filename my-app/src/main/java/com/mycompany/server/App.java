package com.mycompany.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App 
{
    public static void main( String[] args ){
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ServerApp serverApp = (ServerApp) context.getBean("serverApp");
        serverApp.run();
    }
}

