package io.github.arthurhoch.purevalue.trade.hscode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for HSCode.
 */
class HSCodeTest {
    @Test
    void shouldAcceptValidHSCode6() {
        HSCode hs = HSCode.of("123456");
        assertEquals("123456", hs.value());
    }

    @Test
    void shouldAcceptValidHSCode8() {
        HSCode hs = HSCode.of("12345678");
        assertEquals("12345678", hs.value());
    }

    @Test
    void shouldAcceptValidHSCode10() {
        HSCode hs = HSCode.of("1234567890");
        assertEquals("1234567890", hs.value());
    }

    @Test
    void shouldRejectInvalidHSCode() {
        assertThrows(IllegalArgumentException.class, () -> HSCode.of("12345"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("123456", HSCode.of(" 123456 ").format());
    }
}
