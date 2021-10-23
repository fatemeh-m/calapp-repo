package com.mycompany.server;

import com.mycompany.server.config.AppConfig;
import com.mycompany.server.server.ServerApp;
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

