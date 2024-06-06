package com.tech_challenge_04.customers.service;

import com.tech_challenge_04.customers.entity.Customer;
import com.tech_challenge_04.customers.entity.dtos.CustomerDto;
import com.tech_challenge_04.customers.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(CustomerDto customerDto) {
        return customerRepository.save(new Customer(customerDto));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer findByCpf(String cpf) {
        return customerRepository.findByCpf(cpf);
    }

    public Customer updateCustomer(String cpf, CustomerDto customerDto) {
        var customer = customerRepository.findByCpf(cpf);

        if (Objects.isNull(customer)) {
            throw new EntityNotFoundException();
        }

        customer.updateCustomer(customerDto);

        return customerRepository.save(customer);
    }

    public void deleteCustomer(String cpf) {
        var customer = customerRepository.findByCpf(cpf);

        if (Objects.isNull(customer)) {
            throw new EntityNotFoundException();
        }

        customerRepository.delete(customer);
    }
}
