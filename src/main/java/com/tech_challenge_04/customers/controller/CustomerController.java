package com.tech_challenge_04.customers.controller;

import com.tech_challenge_04.customers.entity.Customer;
import com.tech_challenge_04.customers.entity.dtos.CreateCustomerDto;
import com.tech_challenge_04.customers.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    @Transactional
    ResponseEntity<Customer> createUser(@RequestBody @Valid CreateCustomerDto createCustomerDto) {
        return new ResponseEntity<>(customerService.createCustomer(createCustomerDto), HttpStatus.CREATED);
    }
}
