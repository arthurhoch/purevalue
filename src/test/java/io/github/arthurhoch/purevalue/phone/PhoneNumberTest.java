package io.github.arthurhoch.purevalue.phone;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PhoneNumber (E.164).
 */
class PhoneNumberTest {

    @Test
    void shouldAcceptValidPhoneNumber() {
        // Example valid E.164 phone number (US): +14155552671
        PhoneNumber phone = PhoneNumber.of("+14155552671");
        assertEquals("+14155552671", phone.value());
    }

    @Test
    void shouldAcceptFormattedPhoneNumber() {
        // Example with spaces, dashes, and parentheses; should be cleaned.
        PhoneNumber phone = PhoneNumber.of("+1 (415) 555-2671");
        assertEquals("+14155552671", phone.value());
    }

    @Test
    void shouldRejectInvalidPhoneNumber() {
        // Missing plus sign
        assertThrows(IllegalArgumentException.class, () -> PhoneNumber.of("14155552671"));
        // Too many digits (more than 15 digits after '+')
        assertThrows(IllegalArgumentException.class, () -> PhoneNumber.of("+1234567890123456"));
    }

    @Test
    void shouldFormatCorrectly() {
        // Formatting returns the cleaned number
        assertEquals("+14155552671", PhoneNumber.of(" +1 415-555-2671 ").format());
    }
}
