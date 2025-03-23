// File: AadhaarTest.java
package io.github.arthurhoch.purevalue.in.aadhaar;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Aadhaar.
 */
class AadhaarTest {

    @Test
    void shouldAcceptValidAadhaar() {
        // Valid Aadhaar: "123456789010" passes the Verhoeff check
        Aadhaar aadhaar = Aadhaar.of("123456789010");
        assertEquals("123456789010", aadhaar.value());
    }

    @Test
    void shouldRejectInvalidAadhaar() {
        // Changing the check digit should cause rejection.
        assertThrows(IllegalArgumentException.class, () -> Aadhaar.of("123456789011"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("123456789010", Aadhaar.clean("123 456 789 010"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("123456789010", Aadhaar.of("123456789010").format());
    }
}
