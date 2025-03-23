package io.github.arthurhoch.purevalue.finance.swift;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SWIFT.
 */
class SWIFTTest {
    @Test
    void shouldAcceptValidSWIFT8() {
        SWIFT swift = SWIFT.of("DEUTDEFF");
        assertEquals("DEUTDEFF", swift.value());
    }

    @Test
    void shouldAcceptValidSWIFT11() {
        SWIFT swift = SWIFT.of("DEUTDEFF500");
        assertEquals("DEUTDEFF500", swift.value());
    }

    @Test
    void shouldRejectInvalidSWIFT() {
        assertThrows(IllegalArgumentException.class, () -> SWIFT.of("DEUTD"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("DEUTDEFF500", SWIFT.of(" deutdeff500 ").format());
    }
}
