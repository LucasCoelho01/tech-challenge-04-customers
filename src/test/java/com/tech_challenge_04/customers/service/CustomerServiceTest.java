package com.tech_challenge_04.customers.service;

import com.tech_challenge_04.customers.entity.Customer;
import com.tech_challenge_04.customers.entity.dtos.CustomerDto;
import com.tech_challenge_04.customers.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private CustomerDto customerDto;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customerDto = new CustomerDto("Lucas", "12345678910", "lucas@email.com");
        customer = new Customer(customerDto);
    }

    @Test
    void createCustomer_success() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer createdCustomer = customerService.createCustomer(customerDto);

        assertNotNull(createdCustomer);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void getAllCustomers_success() {
        when(customerRepository.findAll()).thenReturn(List.of(customer));

        List<Customer> customers = customerService.getAllCustomers();

        assertNotNull(customers);
        assertFalse(customers.isEmpty());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void findByCpf_success() {
        String cpf = "123456789";
        when(customerRepository.findByCpf(cpf)).thenReturn(customer);

        Customer foundCustomer = customerService.findByCpf(cpf);

        assertNotNull(foundCustomer);
        assertEquals(customer, foundCustomer);
        verify(customerRepository, times(1)).findByCpf(cpf);
    }

    @Test
    void updateCustomer_success() {
        String cpf = "123456789";
        when(customerRepository.findByCpf(cpf)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer updatedCustomer = customerService.updateCustomer(cpf, customerDto);

        assertNotNull(updatedCustomer);
        verify(customerRepository, times(1)).findByCpf(cpf);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void updateCustomer_notFound() {
        String cpf = "123456789";
        when(customerRepository.findByCpf(cpf)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> customerService.updateCustomer(cpf, customerDto));

        verify(customerRepository, times(1)).findByCpf(cpf);
        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    void deleteCustomer_success() {
        String cpf = "123456789";
        when(customerRepository.findByCpf(cpf)).thenReturn(customer);

        customerService.deleteCustomer(cpf);

        verify(customerRepository, times(1)).findByCpf(cpf);
        verify(customerRepository, times(1)).delete(customer);
    }

    @Test
    void deleteCustomer_notFound() {
        String cpf = "123456789";
        when(customerRepository.findByCpf(cpf)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> customerService.deleteCustomer(cpf));

        verify(customerRepository, times(1)).findByCpf(cpf);
        verify(customerRepository, never()).delete(any(Customer.class));
    }
}
