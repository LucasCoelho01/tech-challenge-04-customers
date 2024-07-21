package com.tech_challenge_04.customers.entity;

import com.tech_challenge_04.customers.entity.dtos.CustomerDto;
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
    private boolean active = true;

    public Customer() {}

    public Customer(CustomerDto customerDto) {
        this.username = customerDto.username();
        this.cpf = customerDto.cpf();
        this.email = customerDto.email();
    }

    public void updateCustomer(CustomerDto customerDto) {
        this.username = customerDto.username();
        this.cpf = customerDto.cpf();
        this.email = customerDto.email();
    }
}
