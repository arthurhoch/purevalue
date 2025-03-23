package io.github.arthurhoch.purevalue.book;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ISMN.
 */
class ISMNTest {
    @Test
    void shouldAcceptValidISMN() {
        ISMN ismn = ISMN.of("9790000000001"); // Corrigido
        assertEquals("9790000000001", ismn.value());
    }

    @Test
    void shouldRejectInvalidISMN() {
        assertThrows(IllegalArgumentException.class, () -> ISMN.of("9790000000006"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("9790000000001", ISMN.of("9790000000001").format());
    }
}