package io.github.arthurhoch.purevalue.ca;

import io.github.arthurhoch.purevalue.ca.transit.TransitNumberCanada;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Transit Number Canada.
 */
class TransitNumberCanadaTest {
    @Test
    void shouldAcceptValidTransitNumber() {
        TransitNumberCanada tn = TransitNumberCanada.of("12345");
        assertEquals("12345", tn.value());
    }

    @Test
    void shouldRejectInvalidTransitNumber() {
        assertThrows(IllegalArgumentException.class, () -> TransitNumberCanada.of("1234"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("12345", TransitNumberCanada.of(" 12345 ").format());
    }
}
