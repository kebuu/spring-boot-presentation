package com.kebuu.springboot.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="game.secret")
@Data
public class StepConfig {

    private String step2;
    private String step3;
}
