package com.kebuu.springboot;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import javax.servlet.Filter;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@PropertySource("classpath:/git.properties")
public class Server {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Server.class, args);
    }

    @Bean
    public Filter corsFilter() {
        return new CorsFilter();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}