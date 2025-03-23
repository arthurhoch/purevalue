package io.github.arthurhoch.purevalue.fr;

import io.github.arthurhoch.purevalue.fr.siret.SIRET;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SIRET.
 */
class SIRETTest {
    @Test
    void shouldAcceptValidSIRET() {
        // Example valid SIRET "73282932000074" (assumed valid per Luhn).
        SIRET siret = SIRET.of("73282932000074");
        assertEquals("73282932000074", siret.value());
    }

    @Test
    void shouldRejectInvalidSIRET() {
        assertThrows(IllegalArgumentException.class, () -> SIRET.of("73282932000075"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("73282932000074", SIRET.of("732 829 320 00074").format());
    }
}