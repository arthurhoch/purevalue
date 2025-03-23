// File: EINTest.java
package io.github.arthurhoch.purevalue.us;

import io.github.arthurhoch.purevalue.us.ein.EIN;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for EIN.
 */
class EINTest {

    @Test
    void shouldAcceptValidEIN() {
        EIN ein = EIN.of("12-3456789");
        assertEquals("123456789", ein.value());
    }

    @Test
    void shouldRejectInvalidEIN() {
        // EIN starting with "00" is invalid
        assertThrows(IllegalArgumentException.class, () -> EIN.of("00-1234567"));
        // EIN with invalid length
        assertThrows(IllegalArgumentException.class, () -> EIN.of("12345678"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("123456789", EIN.clean("12 3456789"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("12-3456789", EIN.of("123456789").format());
    }

    @Test
    void shouldIdentifyFormatted() {
        assertTrue(EIN.isFormatted("12-3456789"));
    }
}
