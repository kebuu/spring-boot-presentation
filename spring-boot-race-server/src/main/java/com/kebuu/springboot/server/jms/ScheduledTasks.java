package com.kebuu.springboot.server.jms;

import com.kebuu.springboot.server.config.StepConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

@Component
public class ScheduledTasks {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private StepConfig stepConfig;

    @Autowired
    private Destination step3Destintation;

    @Scheduled(fixedRate = 2000)
    public void sendStep3SecretMessage() {
        jmsTemplate.convertAndSend(step3Destintation, stepConfig.getStep3());
    }
}