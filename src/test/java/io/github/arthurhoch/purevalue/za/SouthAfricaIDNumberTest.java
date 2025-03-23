// File: SouthAfricaIDNumberTest.java
package io.github.arthurhoch.purevalue.za;

import io.github.arthurhoch.purevalue.za.id.SouthAfricaIDNumber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for South Africa ID Number.
 */
class SouthAfricaIDNumberTest {

    @Test
    void shouldAcceptValidIDNumber() {
        // Hypothetical valid ID number. For example, "8001015009087" is assumed valid.
        SouthAfricaIDNumber idNumber = SouthAfricaIDNumber.of("8001015009087");
        assertEquals("8001015009087", idNumber.value());
    }

    @Test
    void shouldRejectInvalidIDNumber() {
        assertThrows(IllegalArgumentException.class, () -> SouthAfricaIDNumber.of("8001015009086"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("8001015009087", SouthAfricaIDNumber.clean("800101-5009087"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("8001015009087", SouthAfricaIDNumber.of("8001015009087").format());
    }
}
