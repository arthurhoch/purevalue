package io.github.arthurhoch.purevalue.gen;

import io.github.arthurhoch.purevalue.gen.passport.PassportNumber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PassportNumber.
 */
class PassportNumberTest {
    @Test
    void shouldAcceptValidPassportNumber() {
        PassportNumber p = PassportNumber.of("AB123456");
        assertEquals("AB123456", p.value());
    }

    @Test
    void shouldRejectInvalidPassportNumber() {
        assertThrows(IllegalArgumentException.class, () -> PassportNumber.of("A1234"));
        assertThrows(IllegalArgumentException.class, () -> PassportNumber.of("ABCD1234567"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("AB123456", PassportNumber.of(" ab 123456 ").format());
    }
}
