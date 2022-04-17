package com.example.library;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "author") String author
    ) {
        Book book = new Book(id++, name, author);
        library.addBook(book);
    }

    @PatchMapping(value = "/update/{id}", consumes = {"application/json"})
    public void updateBook(
            @PathVariable int id,
            @RequestBody Book book
    ) {
        Book originalBook = library.getBookById(id);

        String name = (book.getName() != null)
                ? book.getName()
                : originalBook.getName();

        String author = (book.getAuthor() != null)
                ? book.getAuthor()
                : originalBook.getAuthor();

        Book newBook = new Book(id, name, author);
        library.updateBook(id, newBook);
    }

    @DeleteMapping("delete/{id}")
    public void deleteBook(@PathVariable int id) {
        library.deleteBook(id);
    }
}
