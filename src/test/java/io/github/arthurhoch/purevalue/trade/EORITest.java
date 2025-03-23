package io.github.arthurhoch.purevalue.trade;

import io.github.arthurhoch.purevalue.trade.eori.EORI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for EORI.
 */
class EORITest {
    @Test
    void shouldAcceptValidEORI() {
        EORI eori = EORI.of("GB123456789012");
        assertEquals("GB123456789012", eori.value());
    }

    @Test
    void shouldRejectInvalidEORI() {
        assertThrows(IllegalArgumentException.class, () -> EORI.of("G123456789012"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("GB123456789012", EORI.of(" gb123456789012 ").format());
    }
}
