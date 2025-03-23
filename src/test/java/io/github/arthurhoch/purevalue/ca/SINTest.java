// File: SINTest.java
package io.github.arthurhoch.purevalue.ca;

import io.github.arthurhoch.purevalue.ca.sin.SIN;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SIN.
 */
class SINTest {

    @Test
    void shouldAcceptValidSIN() {
        // Example valid SIN: "046454286" (a known valid SIN).
        SIN sin = SIN.of("046454286");
        assertEquals("046454286", sin.value());
    }

    @Test
    void shouldRejectInvalidSIN() {
        assertThrows(IllegalArgumentException.class, () -> SIN.of("046454287"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("046454286", SIN.clean("046 454 286"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("046454286", SIN.of("046454286").format());
    }
}
