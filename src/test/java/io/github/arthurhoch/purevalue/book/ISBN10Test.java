package io.github.arthurhoch.purevalue.book;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ISBN-10.
 */
class ISBN10Test {

    @Test
    void shouldAcceptValidISBN10() {
        ISBN10 isbn = ISBN10.of("0306406152"); // Known valid ISBN-10
        assertEquals("0306406152", isbn.value());
    }

    @Test
    void shouldRejectInvalidISBN10() {
        assertThrows(IllegalArgumentException.class, () -> ISBN10.of("0306406153"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("0306406152", ISBN10.of("0-306-40615-2").format());
    }
}
