package com.kebuu.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import javax.jms.ConnectionFactory;

@Configuration
public class Step3Helper {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public MessageListenerAdapter adapter() {
        MessageListenerAdapter messageListener = new MessageListenerAdapter(new Receiver());
        messageListener.setDefaultListenerMethod("receiveMessage");
        return messageListener;
    }

    @Bean
    public SimpleMessageListenerContainer container(MessageListenerAdapter messageListener, ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setMessageListener(messageListener);
        container.setConnectionFactory(connectionFactory);
        container.setDestinationName("spring.boot.race.step3");
        container.setPubSubDomain(true);
        return container;
    }
}
