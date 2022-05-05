package com.example.library;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> handleBookNotFound(BookNotFoundException exception) {
        ExceptionResponseEntity exceptionResponseEntity = new ExceptionResponseEntity(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                exception.getMessage()
        );

        return new ResponseEntity<>(
                exceptionResponseEntity,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(RequestValidationException.class)
    protected ResponseEntity<Object> handleInvalidRequest(RequestValidationException exception) {
        ExceptionResponseEntity exceptionResponseEntity = new ExceptionResponseEntity(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                exception.errorMessages
        );

        return new ResponseEntity<>(
                exceptionResponseEntity,
                HttpStatus.BAD_REQUEST
        );
    }
}
