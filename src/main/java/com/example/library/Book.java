package com.example.library;

public class Book {
    private int id;
    private String name;
    private String author;

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

    Book(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
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
}
