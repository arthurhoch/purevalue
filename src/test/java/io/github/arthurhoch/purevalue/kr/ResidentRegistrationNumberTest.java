// File: ResidentRegistrationNumberTest.java
package io.github.arthurhoch.purevalue.kr.rrn;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Resident Registration Number.
 */
class ResidentRegistrationNumberTest {

    @Test
    void shouldAcceptValidRRN() {
        // Hypothetical valid RRN.
        // For example, using first 12 digits "900101123456" that yield a check digit '8'
        String validRRN = "9001011234568";
        ResidentRegistrationNumber rrn = ResidentRegistrationNumber.of(validRRN);
        assertEquals(validRRN, rrn.value());
    }

    @Test
    void shouldRejectInvalidRRN() {
        // Wrong check digit.
        assertThrows(IllegalArgumentException.class, () -> ResidentRegistrationNumber.of("9001011234567"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("9001011234568", ResidentRegistrationNumber.clean("900-101-123456-8"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("9001011234568", ResidentRegistrationNumber.of("9001011234568").format());
    }
}
