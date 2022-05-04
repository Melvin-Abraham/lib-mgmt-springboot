package com.example.library;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
public class LibraryController {
    private final Library library;
    private int id;

    LibraryController() {
        library = new Library();

        // Add dummy books for demo
        library.addBook(new Book(10290, "Steve Jobs", "Walter Isaacson"));
        library.addBook(new Book(15768, "Refactoring", "Kent Beck and Martin Fowler"));

        id = 1;
    }

    @GetMapping("/")
    public HashMap<String, String> apiDoc() {
        HashMap<String, String> map = new HashMap<>();

        map.put("[GET] /", "Get basic API documentation");
        map.put("[GET] /books", "Get list of all the books");
        map.put("[GET] /books/{id}", "Get particular book based on book ID");
        map.put("[POST] /books", "Add a new book. Required params: name & author");
        map.put("[PATCH] /books/{id}", "Updates a book. Consumes JSON request body containing the details to patch");
        map.put("[DELETE] /books/{id}", "Deletes a book");

        return map;
    }

    @GetMapping("/books")
    public List<Book> books() {
        return library.getBooks();
    }

    @GetMapping("/books/{id}")
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

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(
            @Valid @RequestBody Book book,
            Errors errors
    ) {
        if (errors.hasErrors()) {
            System.out.println("ERRORS!!");
        }

        System.out.println(errors);

        book.setId(id++);
        library.addBook(book);
    }

    @PatchMapping(value = "/books/{id}")
    public void updateBook(
            @PathVariable int id,
            @RequestBody Book partialBook
    ) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        try {
            Book originalBook = library.getBookById(id);
            Book newBook = originalBook.patch(partialBook);

            Set<ConstraintViolation<Book>> violations = validator.validate(newBook);

            if (!violations.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            library.updateBook(id, newBook);
        }
        catch (BookNotFoundException exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    exception.getMessage()
            );
        }
    }

    @DeleteMapping("/books/{id}")
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
