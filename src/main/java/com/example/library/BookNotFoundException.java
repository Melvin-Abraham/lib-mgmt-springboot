package com.example.library;

public class BookNotFoundException extends Exception {
    BookNotFoundException(String message) {
        super(message);
    }
}
