package com.mycompany.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:application.properties")
public class PropertyLoader {
    private Environment environment;

    public PropertyLoader(@AutoWired Environment env){
        environment = env;
    }

    public String getProperty(String key) {
        return environment.getProperty(key);
    }
}





