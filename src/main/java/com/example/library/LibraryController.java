package com.example.library;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibraryController {
    private final Library library;
    private int id;

    LibraryController() {
        library = new Library();
        id = 1;
    }

    @GetMapping("/")
    public List<Book> books() {
        return library.getBooks();
    }

    @PostMapping("/add")
    public void addBook(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "author") String author
    ) {
        Book book = new Book(id++, name, author);
        library.addBook(book);
    }
}
