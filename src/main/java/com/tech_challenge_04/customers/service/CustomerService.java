package com.tech_challenge_04.customers.service;

import com.tech_challenge_04.customers.entity.Customer;
import com.tech_challenge_04.customers.entity.dtos.CreateCustomerDto;
import com.tech_challenge_04.customers.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    public Customer createCustomer(CreateCustomerDto createCustomerDto) {
        return customerRepository.save(new Customer(createCustomerDto));
    }
}
