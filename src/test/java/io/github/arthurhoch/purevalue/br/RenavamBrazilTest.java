// File: RenavamBrazilTest.java
package io.github.arthurhoch.purevalue.br.vehicle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for RenavamBrazil.
 */
class RenavamBrazilTest {

    @Test
    void shouldAcceptValidRenavamBrazilOldFormat() {
        // "123456789" is valid in the old format
        RenavamBrazil renavam = RenavamBrazil.of("123456789");
        assertEquals("123456789", renavam.value());
    }

    @Test
    void shouldAcceptValidRenavamBrazilNewFormat() {
        // Example for new format: base "0123456789" produces a check digit of 7
        RenavamBrazil renavam = RenavamBrazil.of("01234567897");
        assertEquals("01234567897", renavam.value());
    }

    @Test
    void shouldRejectInvalidRenavamBrazil() {
        // Change the check digit to trigger an invalid Renavam in old format
        assertThrows(IllegalArgumentException.class, () -> RenavamBrazil.of("123456788"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("123456789", RenavamBrazil.clean("123456789"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("123456789", RenavamBrazil.of("123456789").format());
    }

    @Test
    void shouldIdentifyFormatted() {
        assertTrue(RenavamBrazil.isFormatted("123456789"));
    }
}
