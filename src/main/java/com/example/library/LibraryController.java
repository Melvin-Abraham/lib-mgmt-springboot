package com.example.library;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/book")
    public Book getBookById(
            @RequestParam(name = "id") int id
    ) {
        Book book = library.getBookById(id);

        if (book == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No book with requested ID was found"
            );
        }

        return book;
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
