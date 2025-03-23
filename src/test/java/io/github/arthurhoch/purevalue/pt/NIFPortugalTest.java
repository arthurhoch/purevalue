// File: NIFPortugalTest.java
package io.github.arthurhoch.purevalue.pt;

import io.github.arthurhoch.purevalue.pt.nif.NIFPortugal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for NIFPortugal.
 */
class NIFPortugalTest {

    @Test
    void shouldAcceptValidNIF() {
        // For payload "12345678": 1*9+2*8+3*7+4*6+5*5+6*4+7*3+8*2 = 156, 156 mod 11 = 2, check digit = 11-2 = 9.
        NIFPortugal nif = NIFPortugal.of("123456789");
        assertEquals("123456789", nif.value());
    }

    @Test
    void shouldRejectInvalidNIF() {
        assertThrows(IllegalArgumentException.class, () -> NIFPortugal.of("123456788"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("123456789", NIFPortugal.clean("123 456 789"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("123456789", NIFPortugal.of("123456789").format());
    }
}
