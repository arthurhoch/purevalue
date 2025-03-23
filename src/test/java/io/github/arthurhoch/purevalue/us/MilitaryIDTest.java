package io.github.arthurhoch.purevalue.us;

import io.github.arthurhoch.purevalue.us.military.MilitaryID;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for MilitaryID.
 */
class MilitaryIDTest {
    @Test
    void shouldAcceptValidMilitaryID() {
        MilitaryID mid = MilitaryID.of("MIL12345");
        assertEquals("MIL12345", mid.value());
    }

    @Test
    void shouldRejectInvalidMilitaryID() {
        assertThrows(IllegalArgumentException.class, () -> MilitaryID.of("MIL1"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("MIL12345", MilitaryID.of(" mil 12345 ").format());
    }
}