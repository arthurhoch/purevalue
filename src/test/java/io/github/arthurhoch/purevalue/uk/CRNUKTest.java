package io.github.arthurhoch.purevalue.uk;

import io.github.arthurhoch.purevalue.uk.crn.CRNUK;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CRNUK.
 */
class CRNUKTest {
    @Test
    void shouldAcceptValidCRN_DigitsOnly() {
        CRNUK crn = CRNUK.of("12345678");
        assertEquals("12345678", crn.value());
    }

    @Test
    void shouldAcceptValidCRN_LettersAndDigits() {
        CRNUK crn = CRNUK.of("AB123456");
        assertEquals("AB123456", crn.value());
    }

    @Test
    void shouldRejectInvalidCRN() {
        assertThrows(IllegalArgumentException.class, () -> CRNUK.of("A1234567"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("12345678", CRNUK.of("1234 5678").format());
    }
}
