package com.example.library;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books;

    Library() {
        books = new ArrayList<Book>();
    }

    public List<Book> getBooks() {
        return books;
    }
}
