package io.github.arthurhoch.purevalue.it;

import io.github.arthurhoch.purevalue.it.partitaiva.PartitaIVA;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Partita IVA.
 */
class PartitaIVATest {
    @Test
    void shouldAcceptValidPartitaIVA() {
        // Using our computation: For "1234567890", check digit computed is 7.
        PartitaIVA piva = PartitaIVA.of("12345678907");
        assertEquals("12345678907", piva.value());
    }

    @Test
    void shouldRejectInvalidPartitaIVA() {
        assertThrows(IllegalArgumentException.class, () -> PartitaIVA.of("12345678908"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("12345678907", PartitaIVA.of("123 456 789 07").format());
    }
}
