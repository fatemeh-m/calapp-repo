package com.mycompany.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = {"com.mycompany.server"})
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
}
