package io.github.arthurhoch.purevalue.br;

import io.github.arthurhoch.purevalue.br.cnae.CNAE;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CNAETest {

    @Test
    void shouldAcceptValidCNAE() {
        assertDoesNotThrow(() -> CNAE.of("62.01-5-01"));
    }

    @Test
    void shouldRejectInvalidCNAE() {
        assertThrows(IllegalArgumentException.class, () -> CNAE.of("999.999-9-99"));
    }

    @Test
    void shouldIdentifyFormattedCNAE() {
        assertTrue(CNAE.isFormatted("62.01-5-01"));
        assertFalse(CNAE.isFormatted("6201501"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("62.01-5-01", CNAE.of("6201501").format());
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("6201501", CNAE.clean("62.01-5-01"));
    }
}
