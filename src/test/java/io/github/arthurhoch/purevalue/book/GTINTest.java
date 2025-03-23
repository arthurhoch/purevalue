package io.github.arthurhoch.purevalue.book;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for GTIN.
 */
class GTINTest {
    @Test
    void shouldAcceptValidGTIN12() {
        // UPC-A example "036000291452" using UPC-A weighting.
        GTIN gtin = GTIN.of("036000291452");
        assertEquals("036000291452", gtin.value());
    }

    @Test
    void shouldAcceptValidGTIN14() {
        // Using our GTIN-14 algorithm (EAN-13 weighting):
        // For candidate, we choose "10012345678904", whose check digit (computed as 4) is valid.
        GTIN gtin = GTIN.of("10012345678904");
        assertEquals("10012345678904", gtin.value());
    }

    @Test
    void shouldRejectInvalidGTIN() {
        // Change check digit for a 12-digit UPC example: "036000291453" should be rejected.
        assertThrows(IllegalArgumentException.class, () -> GTIN.of("036000291453"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("036000291452", GTIN.of(" 036000291452 ").format());
    }
}
