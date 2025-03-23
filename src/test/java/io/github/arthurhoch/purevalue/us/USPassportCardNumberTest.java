// File: USPassportCardNumberTest.java
package io.github.arthurhoch.purevalue.us;

import io.github.arthurhoch.purevalue.us.passport.USPassportCardNumber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for US Passport Card Number.
 */
class USPassportCardNumberTest {

    @Test
    void shouldAcceptValidPassportCardNumber() {
        USPassportCardNumber passport = USPassportCardNumber.of("A12B34C56");
        assertEquals("A12B34C56", passport.value());
    }

    @Test
    void shouldRejectInvalidPassportCardNumber() {
        // Too short
        assertThrows(IllegalArgumentException.class, () -> USPassportCardNumber.of("12345678"));
        // Too long
        assertThrows(IllegalArgumentException.class, () -> USPassportCardNumber.of("1234567890"));
        // Contains invalid characters
        assertThrows(IllegalArgumentException.class, () -> USPassportCardNumber.of("12*456789"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("A12B34C56", USPassportCardNumber.clean("A12 B34 C56"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("A12B34C56", USPassportCardNumber.of("A12B34C56").format());
    }

    @Test
    void shouldIdentifyFormatted() {
        assertTrue(USPassportCardNumber.isFormatted("A12B34C56"));
    }
}
