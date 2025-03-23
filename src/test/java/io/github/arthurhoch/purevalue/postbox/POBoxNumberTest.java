package io.github.arthurhoch.purevalue.postbox;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for POBoxNumber.
 */
class POBoxNumberTest {
    @Test
    void shouldAcceptValidPOBoxNumber() {
        POBoxNumber pobox = POBoxNumber.of("PO Box 123");
        assertEquals("PO Box 123", pobox.value());
    }

    @Test
    void shouldRejectInvalidPOBoxNumber() {
        // Too long: more than 30 characters
        assertThrows(IllegalArgumentException.class, () -> POBoxNumber.of("PO Box 1234567890123456789012345678901"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("PO Box 123", POBoxNumber.of("  PO Box 123  ").format());
    }
}
