package com.kebuu.springboot;

import org.apache.activemq.command.ActiveMQQueue;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=MessagingApplication.class)
@IntegrationTest
public class MessagingApplicationTest {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void test() throws InterruptedException {
        String messageText = "technozaure";

        Destination destination = new ActiveMQQueue("zenika");
        jmsTemplate.convertAndSend(destination, messageText);
        jmsTemplate.setReceiveTimeout(1000);

        Assertions.assertThat(jmsTemplate.receiveAndConvert(destination)).isEqualTo(messageText);
    }
}
