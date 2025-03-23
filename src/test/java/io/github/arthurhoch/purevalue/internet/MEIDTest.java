package io.github.arthurhoch.purevalue.internet;

import io.github.arthurhoch.purevalue.internet.meid.MEID;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for MEID.
 */
class MEIDTest {

    @Test
    void shouldAcceptValidMEID() {
        // Example valid MEID: 14 hexadecimal characters.
        MEID meid = MEID.of("A0000000000001");
        assertEquals("A0000000000001", meid.value());
    }

    @Test
    void shouldRejectInvalidMEID() {
        assertThrows(IllegalArgumentException.class, () -> MEID.of("G0000000000001")); // 'G' not valid hex.
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("A0000000000001", MEID.of("a0000000000001").format());
    }
}