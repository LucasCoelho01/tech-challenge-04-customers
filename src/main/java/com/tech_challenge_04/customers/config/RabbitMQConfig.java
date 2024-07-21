package com.tech_challenge_04.customers.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "testQueue";
    public static final String QUEUE_CUSTOMER_REQUEST = "customerRequest";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Queue queue_customerRequest() {
        return new Queue(QUEUE_CUSTOMER_REQUEST, true);
    }
}

