package io.github.arthurhoch.purevalue.ch;

import io.github.arthurhoch.purevalue.ch.postal.PostalCodeSwitzerland;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PostalCodeSwitzerland.
 */
class PostalCodeSwitzerlandTest {
    @Test
    void shouldAcceptValidPostalCode() {
        PostalCodeSwitzerland pc = PostalCodeSwitzerland.of("8000");
        assertEquals("8000", pc.value());
    }

    @Test
    void shouldRejectInvalidPostalCode() {
        assertThrows(IllegalArgumentException.class, () -> PostalCodeSwitzerland.of("800"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("8000", PostalCodeSwitzerland.of(" 8000 ").format());
    }
}
