package io.github.arthurhoch.purevalue.es;

import io.github.arthurhoch.purevalue.es.nifbusiness.NIFSpainBusiness;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for NIF Spain Business.
 */
class NIFSpainBusinessTest {
    @Test
    void shouldAcceptValidNIFSpainBusiness() {
        NIFSpainBusiness nif = NIFSpainBusiness.of("A1234567B");
        assertEquals("A1234567B", nif.value());
    }

    @Test
    void shouldRejectInvalidNIFSpainBusiness() {
        assertThrows(IllegalArgumentException.class, () -> NIFSpainBusiness.of("A12345678B"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("A1234567B", NIFSpainBusiness.of(" a1234567b ").format());
    }
}
