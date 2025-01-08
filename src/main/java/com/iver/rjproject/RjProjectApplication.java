package com.iver.rjproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class RjProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(RjProjectApplication.class, args);
    }

}
