package com.example.library;

import org.springframework.validation.Errors;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RequestValidationException extends Exception {
    List<String> errorMessages = new ArrayList<>();

    RequestValidationException(Errors errors) {
        errors.getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            errorMessages.add(errorMessage);
        });
    }

    RequestValidationException(Set<ConstraintViolation<Book>> violations) {
        violations.forEach(violation -> {
            String errorMessage = violation.getMessage();
            errorMessages.add(errorMessage);
        });
    }
}
