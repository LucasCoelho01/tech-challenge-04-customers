package com.tech_challenge_04.customers.controller;

import com.tech_challenge_04.customers.entity.Customer;
import com.tech_challenge_04.customers.entity.dtos.CustomerDto;
import com.tech_challenge_04.customers.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private CustomerDto customerDto;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customerDto = new CustomerDto("Lucas", "12345678910", "lucas@email.com");
        // Initialize createCustomerDto with appropriate values
        customer = new Customer(customerDto);
    }

    @Test
    void createUser_success() {
        when(customerService.createCustomer(any(CustomerDto.class))).thenReturn(customer);

        ResponseEntity<Customer> response = customerController.createUser(customerDto);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customer, response.getBody());
        verify(customerService, times(1)).createCustomer(any(CustomerDto.class));
    }

    @Test
    void getAllCustomers_success() {
        when(customerService.getAllCustomers()).thenReturn(List.of(customer));

        ResponseEntity<List<Customer>> response = customerController.getAllCustomers();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(customerService, times(1)).getAllCustomers();
    }

    @Test
    void findByCpf_success() {
        String cpf = "123456789";
        when(customerService.findByCpf(cpf)).thenReturn(customer);

        ResponseEntity<Customer> response = customerController.findByCpf(cpf);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
        verify(customerService, times(1)).findByCpf(cpf);
    }

    @Test
    void updateCustomer_success() {
        String cpf = "123456789";
        when(customerService.updateCustomer(eq(cpf), any(CustomerDto.class))).thenReturn(customer);

        ResponseEntity<Customer> response = customerController.updateCustomer(cpf, customerDto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
        verify(customerService, times(1)).updateCustomer(eq(cpf), any(CustomerDto.class));
    }

    @Test
    void deleteCustomer_success() {
        String cpf = "123456789";
        doNothing().when(customerService).deleteCustomer(cpf);

        ResponseEntity<?> response = customerController.deleteCustomer(cpf);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(customerService, times(1)).deleteCustomer(cpf);
    }
}
