package io.github.arthurhoch.purevalue.internet;

import io.github.arthurhoch.purevalue.internet.iccid.ICCID;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ICCID.
 */
class ICCIDTest {

    @Test
    void shouldAcceptValidICCID_19Digits() {
        ICCID iccid = ICCID.of("8912345678901234567");
        assertEquals("8912345678901234567", iccid.value());
    }

    @Test
    void shouldAcceptValidICCID_20Digits() {
        ICCID iccid = ICCID.of("89123456789012345678");
        assertEquals("89123456789012345678", iccid.value());
    }

    @Test
    void shouldRejectInvalidICCID() {
        assertThrows(IllegalArgumentException.class, () -> ICCID.of("891234567890123456"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("8912345678901234567", ICCID.of("8912 3456 7890 1234 567").format());
    }
}
