package io.github.arthurhoch.purevalue.internet;

import io.github.arthurhoch.purevalue.internet.msisdn.MSISDN;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for MSISDN.
 */
class MSISDNTest {
    @Test
    void shouldAcceptValidMSISDN() {
        MSISDN msisdn = MSISDN.of("+14155552671");
        assertEquals("+14155552671", msisdn.value());
    }

    @Test
    void shouldRejectInvalidMSISDN() {
        assertThrows(IllegalArgumentException.class, () -> MSISDN.of("14155552671")); // missing +
        assertThrows(IllegalArgumentException.class, () -> MSISDN.of("+1415555267112345")); // too long
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("+14155552671", MSISDN.of(" +14155552671 ").format());
    }
}
