package com.tech_challenge_04.customers.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_CUSTOMER_REQUEST = "customerRequest";

    public static final String QUEUE_CUSTOMER_RESPONSE = "customerResponse";

    @Bean
    public Queue queue_customerRequest() {
        return new Queue(QUEUE_CUSTOMER_REQUEST, true);
    }

    @Bean
    public Queue queue_customerResponse() {
        return new Queue(QUEUE_CUSTOMER_RESPONSE, true);
    }
}

