package com.example.library;

import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    void shouldBeAbleToReturnAllBooks() {
        Library library = new Library();
        Book firstBook = new Book(1, "Book 1", "Author 1");
        Book secondBook = new Book(2, "Book 2", "Author 2");

        library.addBook(firstBook);
        library.addBook(secondBook);
        List<Book> allBooks = library.getBooks();

        // Check if the length of books returned match the number of books added
        assertEquals(2, allBooks.size());
    }
}
