package com.kebuu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
public class SpringDataH2Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringDataH2Application.class, args);
    }

}
