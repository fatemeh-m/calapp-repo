package com.mycompany.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;


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





