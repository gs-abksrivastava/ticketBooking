package com.gainsight.ticketbooking.config;


import com.gainsight.ticketbooking.service.Listener;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Autowired
    Listener listener;

    @Bean("studentQueue")
    public Queue studentQueue(){
        return new Queue("bootcamper-sri.student.queue",true);
    }

    @Bean("employeeQueue()")
    public Queue employeeQueue(){
        return new Queue("bootcamper-sri.employee.queue",true);
    }



    @Bean
    ConnectionFactory connectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        return cachingConnectionFactory;
    }

    @Bean
    MessageListenerContainer messageListenerContainer(){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setQueues(studentQueue(),employeeQueue());

        simpleMessageListenerContainer.setMessageListener(listener);
        return simpleMessageListenerContainer;
    }

}
