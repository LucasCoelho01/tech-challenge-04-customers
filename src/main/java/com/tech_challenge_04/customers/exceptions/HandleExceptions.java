package com.tech_challenge_04.customers.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class HandleExceptions {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleException404() {
        return ResponseEntity.notFound().build();
    }

}
