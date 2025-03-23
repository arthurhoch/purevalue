package io.github.arthurhoch.purevalue.ma;

import io.github.arthurhoch.purevalue.ma.cin.MoroccoCIN;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Morocco CIN.
 */
class MoroccoCINTest {
    @Test
    void shouldAcceptValidMoroccoCIN() {
        MoroccoCIN cin = MoroccoCIN.of("12345678901");
        assertEquals("12345678901", cin.value());
    }

    @Test
    void shouldRejectInvalidMoroccoCIN() {
        assertThrows(IllegalArgumentException.class, () -> MoroccoCIN.of("1234567890"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("12345678901", MoroccoCIN.of("123-456-78901").format());
    }
}