package com.tech_challenge_04.customers.entity.dtos;

import com.tech_challenge_04.customers.entity.Customer;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CustomerResponseDto {
    private UUID id;
    private String username;
    private String cpf;
    private String email;
    private boolean active;
    private String orderId;
    private List<String> productNames;

    public CustomerResponseDto(){}

    public CustomerResponseDto(Customer customer, List<String> productNames){
        this.id = customer.getId();
        this.username = customer.getUsername();
        this.cpf = customer.getCpf();
        this.email = customer.getEmail();
        this.active = customer.isActive();
        this.productNames = productNames;
    }
}
