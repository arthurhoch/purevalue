package io.github.arthurhoch.purevalue.ca;

import io.github.arthurhoch.purevalue.ca.postal.PostalCodeCanada;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PostalCodeCanada.
 */
class PostalCodeCanadaTest {
    @Test
    void shouldAcceptValidPostalCode() {
        PostalCodeCanada pc = PostalCodeCanada.of("K1A0B1");
        assertEquals("K1A0B1", pc.value());

        pc = PostalCodeCanada.of("K1A 0B1");
        assertEquals("K1A 0B1", pc.value());
    }

    @Test
    void shouldRejectInvalidPostalCode() {
        assertThrows(IllegalArgumentException.class, () -> PostalCodeCanada.of("123456"));
    }

    @Test
    void shouldFormatCorrectly() {
        // If input is 6 characters without space, output should be formatted with a space.
        assertEquals("K1A 0B1", PostalCodeCanada.of("K1A0B1").format());
    }
}
