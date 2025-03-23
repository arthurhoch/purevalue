package io.github.arthurhoch.purevalue.internet;

import io.github.arthurhoch.purevalue.internet.imei.IMEI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for IMEI.
 */
class IMEITest {

    @Test
    void shouldAcceptValidIMEI() {
        // "490154203237518" is a known valid IMEI.
        IMEI imei = IMEI.of("490154203237518");
        assertEquals("490154203237518", imei.value());
    }

    @Test
    void shouldRejectInvalidIMEI() {
        // Changing the check digit to cause rejection.
        assertThrows(IllegalArgumentException.class, () -> IMEI.of("490154203237517"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("490154203237518", IMEI.of("490-154-203237518").format());
    }
}