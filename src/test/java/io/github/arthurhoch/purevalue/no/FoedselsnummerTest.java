// File: FoedselsnummerTest.java
package io.github.arthurhoch.purevalue.no;

import io.github.arthurhoch.purevalue.no.fnr.Foedselsnummer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Foedselsnummer.
 */
class FoedselsnummerTest {
    @Test
    void shouldAcceptValidFoedselsnummer() {
        // "01020300082" is a valid Foedselsnummer based on the above check digit calculations.
        Foedselsnummer fnr = Foedselsnummer.of("01020300082");
        assertEquals("01020300082", fnr.value());
    }

    @Test
    void shouldRejectInvalidFoedselsnummer() {
        assertThrows(IllegalArgumentException.class, () -> Foedselsnummer.of("01020312344"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("01020312345", Foedselsnummer.clean("010-203-12345"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("01020300082", Foedselsnummer.of("01020300082").format());
    }
}
