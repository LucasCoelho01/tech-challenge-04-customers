package com.tech_challenge_04.customers.entity.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerDto(
        @NotBlank
        String username,
        String cpf,
        @Email
        String email

) {}
