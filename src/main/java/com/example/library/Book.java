package com.example.library;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Book {
    private int id;

    @NotNull
    @NotEmpty
    @Size(min = 10, max = 100, message = "Book name must be between {min} to {max} characters long")
    private final String name;

    @NotNull(message = "Author name must be provided")
    @NotEmpty(message = "Author name must not be empty")
    @Size(min = 10, max = 100, message = "Author name must be between {min} to {max} characters long")
    private final String author;

    Book(
            int id,
            String name,
            String author
    ) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        id = newId;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object obj) {
        Book anotherBook = (Book) obj;

        return (
                this.getId() == anotherBook.getId()
                && this.getName().equals(anotherBook.getName())
                && this.getAuthor().equals(anotherBook.getAuthor())
        );
    }

    public Book patch(Book partialBook) {
        String resolvedName = partialBook.getName() != null ? partialBook.getName() : this.getName();
        String resolvedAuthor = partialBook.getAuthor() != null ? partialBook.getAuthor() : this.getAuthor();

        Book patchedBook = new Book(
                this.id,
                resolvedName,
                resolvedAuthor
        );

        return patchedBook;
    }
}
