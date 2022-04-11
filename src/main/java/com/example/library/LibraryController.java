package com.example.library;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibraryController {
    private final Library library;

    LibraryController() {
        library = new Library();
    }

    @GetMapping("/")
    public List<Book> books() {
        return library.getBooks();
    }
}
