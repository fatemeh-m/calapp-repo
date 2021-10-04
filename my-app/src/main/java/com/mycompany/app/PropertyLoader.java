package com.mycompany.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:application.properties")
public class PropertyLoader {

    @Value("${operators.packageName}")
    private String path;

    public String getPath() {
        return path;
    }
}





