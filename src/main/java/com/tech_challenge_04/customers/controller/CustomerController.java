package com.tech_challenge_04.customers.controller;

import com.tech_challenge_04.customers.entity.Customer;
import com.tech_challenge_04.customers.entity.dtos.CustomerDto;
import com.tech_challenge_04.customers.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    @Transactional
    ResponseEntity<Customer> createUser(@RequestBody @Valid CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{cpf}")
    ResponseEntity<Customer> findByCpf(@PathVariable String cpf) {
        return new ResponseEntity<>(customerService.findByCpf(cpf), HttpStatus.OK);
    }

    @PutMapping("/{cpf}")
    @Transactional
    ResponseEntity<Customer> updateCustomer(@PathVariable String cpf, @RequestBody @Valid CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.updateCustomer(cpf, customerDto), HttpStatus.OK);
    }

    @DeleteMapping("/{cpf}")
    @Transactional
    ResponseEntity deleteCustomer(@PathVariable String cpf) {
        customerService.deleteCustomer(cpf);
        return new ResponseEntity(HttpStatus.OK);
    }
}
