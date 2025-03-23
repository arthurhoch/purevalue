// File: NationalIDEgyptTest.java
package io.github.arthurhoch.purevalue.eg;

import io.github.arthurhoch.purevalue.eg.nationalid.NationalIDEgypt;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for National IDEgypt.
 */
class NationalIDEgyptTest {

    @Test
    void shouldAcceptValidNationalIDEgypt() {
        // Hypothetical valid Egyptian National ID (14 digits).
        NationalIDEgypt id = NationalIDEgypt.of("29807010123456");
        assertEquals("29807010123456", id.value());
    }

    @Test
    void shouldRejectInvalidNationalIDEgypt() {
        assertThrows(IllegalArgumentException.class, () -> NationalIDEgypt.of("1234567890123"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("29807010123456", NationalIDEgypt.clean("29807-010123456"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("29807010123456", NationalIDEgypt.of("29807010123456").format());
    }
}
