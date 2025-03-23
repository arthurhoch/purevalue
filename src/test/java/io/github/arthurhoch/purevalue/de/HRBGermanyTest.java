package io.github.arthurhoch.purevalue.de;

import io.github.arthurhoch.purevalue.de.hrb.HRBGermany;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for HRB Germany.
 */
class HRBGermanyTest {
    @Test
    void shouldAcceptValidHRB() {
        HRBGermany hrb = HRBGermany.of("HRB123456");
        assertEquals("HRB123456", hrb.value());

        hrb = HRBGermany.of("hrb 98765");
        assertEquals("HRB98765", hrb.value());
    }

    @Test
    void shouldRejectInvalidHRB() {
        assertThrows(IllegalArgumentException.class, () -> HRBGermany.of("HB123456"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("HRB123456", HRBGermany.of("hrb 123456").format());
    }
}
