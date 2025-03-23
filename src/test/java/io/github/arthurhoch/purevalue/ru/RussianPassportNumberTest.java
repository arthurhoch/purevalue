// File: RussianPassportNumberTest.java
package io.github.arthurhoch.purevalue.ru.passport;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for RussianPassportNumber.
 */
class RussianPassportNumberTest {

    @Test
    void shouldAcceptValidRussianPassportNumber9Digits() {
        RussianPassportNumber passport = RussianPassportNumber.of("123456789");
        assertEquals("123456789", passport.value());
    }

    @Test
    void shouldAcceptValidRussianPassportNumber10Digits() {
        RussianPassportNumber passport = RussianPassportNumber.of("1234567890");
        assertEquals("1234567890", passport.value());
    }

    @Test
    void shouldRejectInvalidRussianPassportNumber() {
        assertThrows(IllegalArgumentException.class, () -> RussianPassportNumber.of("ABC123456"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("123456789", RussianPassportNumber.of("123-456-789").format());
    }
}
