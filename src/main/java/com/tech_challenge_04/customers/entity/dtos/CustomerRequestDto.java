package com.tech_challenge_04.customers.entity.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CustomerRequestDto {
    private String orderId;
    private String cpf;
    private List<String> productNames;

    public CustomerRequestDto(){}
}
