package com.example.library;

import org.junit.jupiter.api.Test;

public class BookTest {
    @Test
    void shouldReturnPatchedBookObjectWithOriginalAuthorIfAuthorIsNull() {
        Book originalBook = new Book("Original Name", "Original Author", null, "05-05-2022");
        Book partialBook = new Book("New Name", null, null, null);

        Book patchedBook = originalBook.patch(partialBook);

        Book expectedPatchedBook = new Book("New Name", "Original Author", null, "05-05-2022");
        expectedPatchedBook.setId(originalBook.getId());
        assert patchedBook.equals(expectedPatchedBook);
    }

    @Test
    void shouldReturnPatchedBookObjectAsOriginalIfAllTheFieldsAreNull() {
        Book originalBook = new Book("Original Name", "Original Author", null, "05-05-2022");
        Book partialBook = new Book(null, null, null, null);

        Book patchedBook = originalBook.patch(partialBook);

        Book expectedPatchedBook = new Book("Original Name", "Original Author", null, "05-05-2022");
        expectedPatchedBook.setId(originalBook.getId());
        assert patchedBook.equals(expectedPatchedBook);
    }

    @Test
    void shouldReturnPatchedBookObjectAsCompletelyNewIfAllTheFieldsAreNonNull() {
        Book originalBook = new Book("Original Name", "Original Author", null, "05-05-2022");
        Book partialBook = new Book("New Name", "New Author", "This is a simple book", "01-04-2022");

        Book patchedBook = originalBook.patch(partialBook);

        Book expectedPatchedBook = new Book("New Name", "New Author", "This is a simple book", "01-04-2022");
        expectedPatchedBook.setId(originalBook.getId());
        assert patchedBook.equals(expectedPatchedBook);
    }
}
