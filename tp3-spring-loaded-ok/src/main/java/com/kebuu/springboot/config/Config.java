package com.kebuu.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ReloadedBean reloadedBean() {
        return new ReloadedBean();
    }
}
