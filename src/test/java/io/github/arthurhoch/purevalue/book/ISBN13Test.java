package io.github.arthurhoch.purevalue.book;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ISBN-13.
 */
class ISBN13Test {
    @Test
    void shouldAcceptValidISBN13() {
        ISBN13 isbn = ISBN13.of("9780306406157"); // Known valid ISBN-13
        assertEquals("9780306406157", isbn.value());
    }

    @Test
    void shouldRejectInvalidISBN13() {
        assertThrows(IllegalArgumentException.class, () -> ISBN13.of("9780306406158"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("9780306406157", ISBN13.of("978-0-306-40615-7").format());
    }
}
