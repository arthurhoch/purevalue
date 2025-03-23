package io.github.arthurhoch.purevalue.book;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ISRC.
 */
class ISRCTest {
    @Test
    void shouldAcceptValidISRCWithoutHyphens() {
        // Example: "USRC117607839" is 11 characters; we need 12.
        // A valid ISRC must have 12 characters. We'll use "USRC117607839" (12 characters)
        // Here, "US" + "RC1" + "17" + "607839" (last part 6 digits?) That would be 2+3+2+6 = 13.
        // Let's use a correct 12-character example: "USRC11760783" (2+3+2+5 = 12).
        ISRC isrc = ISRC.of("USRC11760783");
        // Expected formatted output: "US-RC1-17-60783"
        assertEquals("USRC11760783", isrc.value());
        assertEquals("US-RC1-17-60783", isrc.format());
    }

    @Test
    void shouldAcceptValidISRCWithHyphens() {
        ISRC isrc = ISRC.of("US-RC1-17-60783");
        assertEquals("US-RC1-17-60783", isrc.format());
    }

    @Test
    void shouldRejectInvalidISRC() {
        assertThrows(IllegalArgumentException.class, () -> ISRC.of("INVALIDISRC"));
    }
}
