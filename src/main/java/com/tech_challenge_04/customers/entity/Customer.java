package com.tech_challenge_04.customers.entity;

import com.tech_challenge_04.customers.entity.dtos.CreateCustomerDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "customers")

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String cpf;
    private String email;

    public Customer() {}

    public Customer(CreateCustomerDto createCustomerDto) {
        this.username = createCustomerDto.username();
        this.cpf = createCustomerDto.cpf();
        this.email = createCustomerDto.email();
    }

    public void updateCustomer(CreateCustomerDto createCustomerDto) {
        this.username = createCustomerDto.username();
        this.cpf = createCustomerDto.cpf();
        this.email = createCustomerDto.email();
    }
}
