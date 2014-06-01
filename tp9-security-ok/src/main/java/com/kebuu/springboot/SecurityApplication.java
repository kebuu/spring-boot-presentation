package com.kebuu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableAutoConfiguration
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan
public class SecurityApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SecurityApplication.class, args);
    }
}
