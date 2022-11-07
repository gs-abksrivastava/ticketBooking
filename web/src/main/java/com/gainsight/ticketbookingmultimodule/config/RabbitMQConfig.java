package com.gainsight.ticketbookingmultimodule.config;


import ch.qos.logback.classic.pattern.MessageConverter;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean("studentBean")
    public Queue studentQueue(){
        return new Queue("bootcamper-sri.student.queue",true);
    }

    @Bean("employeeBean")
    public Queue employeeQueue(){
        return new Queue("bootcamper-sri.employee.queue",true);
    }

    @Bean
    public Exchange myExchange(){
        return ExchangeBuilder
                .directExchange("bootcamper-sri.exchange")
                .durable(true)
                .build();
    }

    @Bean
    public Binding studentBinding(){
        return BindingBuilder
                .bind(studentQueue())
                .to(myExchange())
                .with("student")
                .noargs();
    }

    @Bean
    public Binding employeeBinding(){
        return BindingBuilder
                .bind(employeeQueue())
                .to(myExchange())
                .with("employee")
                .noargs();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}
