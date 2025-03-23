package io.github.arthurhoch.purevalue.pl;

import io.github.arthurhoch.purevalue.pl.nip.NIP;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for NIP (Poland).
 */
class NIPTest {
    @Test
    void shouldAcceptValidNIP() {
        // Example: "1234563218" (assume checksum calculation yields 8)
        NIP nip = NIP.of("1234563218");
        assertEquals("1234563218", nip.value());
    }

    @Test
    void shouldRejectInvalidNIP() {
        assertThrows(IllegalArgumentException.class, () -> NIP.of("1234563217"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("1234563218", NIP.of("123 456 3218").format());
    }
}
