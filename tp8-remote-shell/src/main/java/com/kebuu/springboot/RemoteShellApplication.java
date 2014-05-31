package com.kebuu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
public class RemoteShellApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RemoteShellApplication.class, args);
    }

}
