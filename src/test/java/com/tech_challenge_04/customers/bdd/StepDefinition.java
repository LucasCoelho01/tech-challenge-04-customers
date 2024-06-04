package com.tech_challenge_04.customers.bdd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech_challenge_04.customers.entity.Customer;
import com.tech_challenge_04.customers.entity.dtos.CreateCustomerDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StepDefinition {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private MvcResult mvcResult;
    private CreateCustomerDto createCustomerDto;

    @Autowired
    private ObjectMapper objectMapper;

    @Given("a customer payload with username {string}, cpf {string} and email {string}")
    public void a_customer_payload_with_name_and_email(String username, String cpf, String email) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        createCustomerDto = new CreateCustomerDto(username, cpf, email);
    }

    @When("the client requests to create a customer")
    public void the_client_requests_to_create_a_customer() throws Exception {
        mvcResult = mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createCustomerDto)))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Then("the response should contain the customer's ID and details")
    public void the_response_should_contain_the_customer_s_id_and_details() throws Exception {
        String content = mvcResult.getResponse().getContentAsString();
        Customer createdCustomer = objectMapper.readValue(content, Customer.class);
        assertThat(createdCustomer.getId()).isNotNull();
        assertThat(createdCustomer.getUsername()).isEqualTo(createCustomerDto.username());
        assertThat(createdCustomer.getEmail()).isEqualTo(createCustomerDto.email());
        assertThat(createdCustomer.getCpf()).isEqualTo(createCustomerDto.cpf());
    }

}
