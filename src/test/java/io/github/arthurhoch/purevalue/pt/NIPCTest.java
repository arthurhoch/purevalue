package io.github.arthurhoch.purevalue.pt;

import io.github.arthurhoch.purevalue.pt.nipc.NIPC;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for NIPC.
 */
class NIPCTest {
    @Test
    void shouldAcceptValidNIPC() {
        // Example: "512345678" where:
        // Assume first 8 digits "51234567" yield sum = 5*9+1*8+2*7+3*6+4*5+5*4+6*3+7*2 = 45+8+14+18+20+20+18+14 = 157;
        // remainder = 157 mod 11 = 3, expected check = 11-3 = 8.
        NIPC nipc = NIPC.of("512345678");
        assertEquals("512345678", nipc.value());
    }

    @Test
    void shouldRejectInvalidNIPC() {
        assertThrows(IllegalArgumentException.class, () -> NIPC.of("412345678")); // first digit not allowed
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("512345678", NIPC.of("512-345-678").format());
    }
}