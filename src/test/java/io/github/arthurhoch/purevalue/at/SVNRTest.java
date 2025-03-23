// File: SVNRTest.java
package io.github.arthurhoch.purevalue.at;

import io.github.arthurhoch.purevalue.at.svnr.SVNR;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SVNR.
 */
class SVNRTest {

    @Test
    void shouldAcceptValidSVNR() {
        // For base "123456789", the weighted sum = 1*1+2*2+...+9*9 = 285; check digit = 285 mod 10 = 5.
        SVNR svnr = SVNR.of("1234567895");
        assertEquals("1234567895", svnr.value());
    }

    @Test
    void shouldRejectInvalidSVNR() {
        assertThrows(IllegalArgumentException.class, () -> SVNR.of("1234567890"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("1234567895", SVNR.of("1234567895").format());
    }
}
