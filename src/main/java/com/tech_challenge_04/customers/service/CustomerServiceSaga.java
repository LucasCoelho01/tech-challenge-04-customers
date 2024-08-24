package com.tech_challenge_04.customers.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tech_challenge_04.customers.config.RabbitMQConfig;
import com.tech_challenge_04.customers.entity.Customer;
import com.tech_challenge_04.customers.entity.dtos.CustomerRequestDto;
import com.tech_challenge_04.customers.entity.dtos.CustomerResponseDto;
import com.tech_challenge_04.customers.repository.CustomerRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceSaga {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();


    @RabbitListener(queues = RabbitMQConfig.QUEUE_CUSTOMER_REQUEST)
    public void handleCustomerMessage(String jsonRequest) {
        System.out.println("Recebido queue QUEUE_CUSTOMER_REQUEST");
        Gson gson = new Gson();
        CustomerResponseDto requestDto = gson.fromJson(jsonRequest, CustomerResponseDto.class);

        System.out.println("CPF recebido: " + requestDto.getCpf());

        Customer customer = customerRepository.findByCpf(requestDto.getCpf());

        CustomerResponseDto customerResponseDto = new CustomerResponseDto(customer, requestDto.getProductNames());
        customerResponseDto.setOrderId(requestDto.getOrderId());

        String json = gson.toJson(customerResponseDto);

        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_CUSTOMER_RESPONSE, json);
        System.out.println("Enviado queue QUEUE_CUSTOMER_RESPONSE");
    }
}
