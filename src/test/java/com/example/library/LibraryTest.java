package com.example.library;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibraryTest {
    @Test
    void shouldBeAbleToAddBook() throws BookNotFoundException {
        Library library = new Library();
        Book newBook = new Book(1, "Test Book", "Test Author", null, "05-05-2022");

        library.addBook(newBook);

        Book retrievedBook = library.getBookById(1);
        assertEquals(retrievedBook, newBook);
    }

    @Test
    void shouldBeAbleToReturnAllBooks() {
        Library library = new Library();
        Book firstBook = new Book(1, "Book 1", "Author 1", null, "05-05-2022");
        Book secondBook = new Book(2, "Book 2", "Author 2", null, "05-05-2022");

        library.addBook(firstBook);
        library.addBook(secondBook);
        List<Book> allBooks = library.getBooks();

        // Check if the length of books returned match the number of books added
        assertEquals(2, allBooks.size());
    }

    @Test
    void shouldBeAbleToUpdateBookBasedOnBookId() throws BookNotFoundException {
        Library library = new Library();
        Book book = new Book(1, "Test Book", "Test Author", null, "05-05-2022");
        library.addBook(book);

        Book updatedBook = new Book(
                book.getId(),
                "Hello World",
                book.getAuthor(),
                null,
                "05-05-2022"
        );
        library.updateBook(book.getId(), updatedBook);
        Book targetBook = library.getBookById(book.getId());

        assertEquals(updatedBook, targetBook);
    }

    @Test
    void shouldBeAbleToDeleteBookBasedOnBookId() throws BookNotFoundException {
        Library library = new Library();
        Book book = new Book(1, "Test Book", "Test Author", null, "05-05-2022");
        library.addBook(book);

        library.deleteBook(book.getId());
        List<Book> allBooks = library.getBooks();

        assertEquals(0, allBooks.size());
    }
}
