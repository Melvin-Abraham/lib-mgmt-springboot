package com.example.library;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books;

    Library() {
        books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book getBookById(int id) throws BookNotFoundException {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }

        throw new BookNotFoundException("Failed to find book with ID: " + id);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void updateBook(int id, Book newBook) throws BookNotFoundException {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);

            if (book.getId() == id) {
                books.set(i, newBook);
                return;
            }
        }

        throw new BookNotFoundException("Failed to find book with ID: " + id);
    }

    public void deleteBook(int id) throws BookNotFoundException {
        Book book = getBookById(id);
        books.remove(book);
    }
}
