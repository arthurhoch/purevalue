package io.github.arthurhoch.purevalue.book;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for EAN-13.
 */
class EAN13Test {
    @Test
    void shouldAcceptValidEAN13() {
        EAN13 ean = EAN13.of("4006381333931"); // Known valid EAN-13
        assertEquals("4006381333931", ean.value());
    }

    @Test
    void shouldRejectInvalidEAN13() {
        assertThrows(IllegalArgumentException.class, () -> EAN13.of("4006381333930"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("4006381333931", EAN13.of("4006381333931").format());
    }
}
