package com.tech_challenge_04.customers.service;

import com.google.gson.Gson;
import com.tech_challenge_04.customers.config.RabbitMQConfig;
import com.tech_challenge_04.customers.repository.CustomerRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class CustomerServiceSaga {
    @Autowired
    private CustomerRepository customerRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String json) {
        Gson gson = new Gson();
        Pessoa pessoa = gson.fromJson(json, Pessoa.class);

        System.out.println("Nome: " + pessoa.nome);
        System.out.println("Idade: " + pessoa.idade);
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_CUSTOMER_REQUEST)
    public void receiveMessage2(String json) {
        Gson gson = new Gson();
        Pessoa pessoa = gson.fromJson(json, Pessoa.class);

        System.out.println("Nome: " + pessoa.nome);
        System.out.println("Idade: " + pessoa.idade);
    }

    record Pessoa(String nome, int idade){

    }
}
