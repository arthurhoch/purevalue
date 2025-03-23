package io.github.arthurhoch.purevalue.internet;

import io.github.arthurhoch.purevalue.internet.imsi.IMSI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for IMSI.
 */
class IMSITest {
    @Test
    void shouldAcceptValidIMSI() {
        IMSI imsi = IMSI.of("310150123456789");
        assertEquals("310150123456789", imsi.value());
    }

    @Test
    void shouldRejectInvalidIMSI() {
        assertThrows(IllegalArgumentException.class, () -> IMSI.of("31015012345678"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("310150123456789", IMSI.of(" 310150123456789 ").format());
    }
}
