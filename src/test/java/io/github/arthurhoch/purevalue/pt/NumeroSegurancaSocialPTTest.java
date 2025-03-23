// File: NumeroSegurancaSocialPTTest.java
package io.github.arthurhoch.purevalue.pt;

import io.github.arthurhoch.purevalue.pt.niss.NumeroSegurancaSocialPT;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for NumeroSegurancaSocialPT.
 */
class NumeroSegurancaSocialPTTest {

    @Test
    void shouldAcceptValidNISS() {
        // For payload "1234567890":
        // 1*29+2*23+3*19+4*17+5*13+6*11+7*7+8*5+9*3+0*2 = 447, 447 mod 10 = 7, check digit = 10 - 7 = 3.
        NumeroSegurancaSocialPT niss = NumeroSegurancaSocialPT.of("12345678903");
        assertEquals("12345678903", niss.value());
    }

    @Test
    void shouldRejectInvalidNISS() {
        assertThrows(IllegalArgumentException.class, () -> NumeroSegurancaSocialPT.of("12345678904"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("12345678903", NumeroSegurancaSocialPT.clean("123 456 789 03"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("12345678903", NumeroSegurancaSocialPT.of("12345678903").format());
    }
}
