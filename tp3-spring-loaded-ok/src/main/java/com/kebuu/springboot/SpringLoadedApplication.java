package com.kebuu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class SpringLoadedApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringLoadedApplication.class, args);
    }
}
