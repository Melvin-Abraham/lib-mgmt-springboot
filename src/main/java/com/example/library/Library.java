package com.example.library;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books;

    Library() {
        books = new ArrayList<Book>();

        // Add dummy books for demo
        addBook(new Book(10290, "Steve Jobs", "Walter Isaacson"));
        addBook(new Book(15768, "Refactoring", "Kent Beck and Martin Fowler"));
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }
}
