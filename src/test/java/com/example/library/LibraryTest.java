package com.example.library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibraryTest {
    @Test
    void shouldBeAbleToAddBook() throws BookNotFoundException {
        Library library = new Library();
        Book newBook = new Book(1, "Test Book", "Test Author");

        library.addBook(newBook);

        Book retrievedBook = library.getBookById(1);
        assertEquals(retrievedBook, newBook);
    }
}
