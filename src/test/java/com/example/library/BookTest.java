package com.example.library;

import org.junit.jupiter.api.Test;

public class BookTest {
    @Test
    void shouldReturnPatchedBookObjectWithOriginalAuthorIfAuthorIsNull() {
        Book originalBook = new Book(1, "Original Name", "Original Author", null, "05-05-2022");
        Book partialBook = new Book(1, "New Name", null, null, null);

        Book patchedBook = originalBook.patch(partialBook);

        Book expectedPatchedBook = new Book(1, "New Name", "Original Author", null, "05-05-2022");
        assert patchedBook.equals(expectedPatchedBook);
    }

    @Test
    void shouldReturnPatchedBookObjectAsOriginalIfAllTheFieldsAreNull() {
        Book originalBook = new Book(1, "Original Name", "Original Author", null, "05-05-2022");
        Book partialBook = new Book(1, null, null, null, null);

        Book patchedBook = originalBook.patch(partialBook);

        Book expectedPatchedBook = new Book(1, "Original Name", "Original Author", null, "05-05-2022");
        assert patchedBook.equals(expectedPatchedBook);
    }

    @Test
    void shouldReturnPatchedBookObjectAsCompletelyNewIfAllTheFieldsAreNonNull() {
        Book originalBook = new Book(1, "Original Name", "Original Author", null, "05-05-2022");
        Book partialBook = new Book(1, "New Name", "New Author", "This is a simple book", "01-04-2022");

        Book patchedBook = originalBook.patch(partialBook);

        Book expectedPatchedBook = new Book(1, "New Name", "New Author", "This is a simple book", "01-04-2022");
        assert patchedBook.equals(expectedPatchedBook);
    }
}
