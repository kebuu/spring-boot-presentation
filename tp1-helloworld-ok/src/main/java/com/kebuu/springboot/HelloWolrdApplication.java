package com.kebuu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class HelloWolrdApplication {

    public static final String HELLO_MESSAGE = "Hello World!";

    @RequestMapping("/")
    String home() {
        return HELLO_MESSAGE;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HelloWolrdApplication.class, args);
    }

}
