// File: NINTest.java
package io.github.arthurhoch.purevalue.uk.nin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for NIN.
 */
class NINTest {

    @Test
    void shouldAcceptValidNIN() {
        // "AB123456C" is valid if prefix "AB" is allowed.
        NIN nin = NIN.of("AB123456C");
        assertEquals("AB123456C", nin.value());
    }

    @Test
    void shouldRejectInvalidNIN_Pattern() {
        // Invalid final letter.
        assertThrows(IllegalArgumentException.class, () -> NIN.of("AB123456E"));
    }

    @Test
    void shouldRejectInvalidNIN_ForbiddenPrefix() {
        // "BG" is forbidden.
        assertThrows(IllegalArgumentException.class, () -> NIN.of("BG123456A"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("AB123456C", NIN.clean(" ab 123456 c "));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("AB123456C", NIN.of("AB123456C").format());
    }
}
