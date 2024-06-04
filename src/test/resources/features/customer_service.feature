Feature: Customer Service

  Scenario: Create a new customer
    Given a customer payload with username "John Doe", cpf "12345678910" and email "john.doe@example.com"
    When the client requests to create a customer
    Then the response should contain the customer's ID and details