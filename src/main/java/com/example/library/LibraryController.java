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

    @GetMapping("/book/{id}")
    public Book getBookById(
            @PathVariable int id
    ) {
        try {
            Book book = library.getBookById(id);
            return book;
        }
        catch (BookNotFoundException exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    exception.getMessage()
            );
        }
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
        try {
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
        catch (BookNotFoundException exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    exception.getMessage()
            );
        }
    }

    @DeleteMapping("delete/{id}")
    public void deleteBook(@PathVariable int id) {
        try {
            library.deleteBook(id);
        }
        catch (BookNotFoundException exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    exception.getMessage()
            );
        }
    }
}
