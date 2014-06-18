package com.kebuu.springboot;

import org.apache.activemq.command.ActiveMQTopic;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.jms.Destination;
import javax.servlet.Filter;
import java.io.IOException;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableScheduling
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
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
            }
        });
        return restTemplate;
    }

    @Bean
    public Destination step3Destination() {
        return new ActiveMQTopic("spring.boot.race.step3");
    }
}