package com.example.library;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @Size(max = 500, message = "Description cannot exceed {max} characters")
    private final String description;

    @NotNull(message = "Publish date must be provided")
    @NotEmpty(message = "Publish date must not be empty")
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Publish date must match the pattern dd-MM-yyyy")
    @ValidDate(message = "Publish date must be valid and should reflect either today or any date before today")
    private final String publishedOn;

    Book(
            int id,
            String name,
            String author,
            String description,
            String publishedOn
    ) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.publishedOn = publishedOn;
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

    public String getDescription() {
        return description;
    }

    public String getPublishedOn() {
        return publishedOn;
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
        String resolvedDescription = partialBook.getDescription() != null ? partialBook.getDescription() : this.getDescription();
        String resolvedPublishDate = partialBook.getPublishedOn() != null ? partialBook.getPublishedOn() : this.getPublishedOn();

        Book patchedBook = new Book(
                this.id,
                resolvedName,
                resolvedAuthor,
                resolvedDescription,
                resolvedPublishDate
        );

        return patchedBook;
    }
}
