package io.github.arthurhoch.purevalue.industry;

import io.github.arthurhoch.purevalue.industry.sic.SICCode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SICCode.
 */
class SICCodeTest {
    @Test
    void shouldAcceptValidSICCode() {
        SICCode sic = SICCode.of("1234");
        assertEquals("1234", sic.value());
    }

    @Test
    void shouldRejectInvalidSICCode() {
        assertThrows(IllegalArgumentException.class, () -> SICCode.of("123"));
        assertThrows(IllegalArgumentException.class, () -> SICCode.of("12345"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("1234", SICCode.of(" 1234 ").format());
    }
}
