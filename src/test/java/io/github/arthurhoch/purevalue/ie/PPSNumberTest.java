// File: PPSNumberTest.java
package io.github.arthurhoch.purevalue.ie;

import io.github.arthurhoch.purevalue.ie.pps.PPSNumber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PPSNumber.
 */
class PPSNumberTest {

    @Test
    void shouldAcceptValidPPSNumber() {
        // For example, for digits "1234567" the weighted sum is:
        // 8*1 + 7*2 + 6*3 + 5*4 + 4*5 + 3*6 + 2*7 = 8 + 14 + 18 + 20 + 20 + 18 + 14 = 112.
        // 112 mod 23 = 112 - (23*4=92) = 20, and LOOKUP.charAt(20) = 'V'
        PPSNumber pps = PPSNumber.of("1234567V");
        assertEquals("1234567V", pps.value());
    }

    @Test
    void shouldRejectInvalidPPSNumber() {
        assertThrows(IllegalArgumentException.class, () -> PPSNumber.of("1234567A"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("1234567V", PPSNumber.of("1234567V").format());
    }
}
