package com.example.library;

import org.junit.jupiter.api.Test;

public class BookTest {
    @Test
    void shouldReturnPatchedBookObjectWithOriginalAuthorIfAuthorIsNull() {
        Book originalBook = new Book(1, "Original Name", "Original Author");
        Book partialBook = new Book(1, "New Name", null);

        Book patchedBook = originalBook.patch(partialBook);

        Book expectedPatchedBook = new Book(1, "New Name", "Original Author");
        assert patchedBook.equals(expectedPatchedBook);
    }

    @Test
    void shouldReturnPatchedBookObjectAsOriginalIfAllTheFieldsAreNull() {
        Book originalBook = new Book(1, "Original Name", "Original Author");
        Book partialBook = new Book(1, null, null);

        Book patchedBook = originalBook.patch(partialBook);

        Book expectedPatchedBook = new Book(1, "Original Name", "Original Author");
        assert patchedBook.equals(expectedPatchedBook);
    }

    @Test
    void shouldReturnPatchedBookObjectAsCompletelyNewIfAllTheFieldsAreNonNull() {
        Book originalBook = new Book(1, "Original Name", "Original Author");
        Book partialBook = new Book(1, "New Name", "New Author");

        Book patchedBook = originalBook.patch(partialBook);

        Book expectedPatchedBook = new Book(1, "New Name", "New Author");
        assert patchedBook.equals(expectedPatchedBook);
    }
}
