// File: USDOTNumberTest.java
package io.github.arthurhoch.purevalue.us.vehicle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for USDOT Number.
 */
class USDOTNumberTest {

    @Test
    void shouldAcceptValidUSDOTNumber() {
        USDOTNumber usdot = USDOTNumber.of("123456");
        assertEquals("123456", usdot.value());
        usdot = USDOTNumber.of("1234567");
        assertEquals("1234567", usdot.value());
    }

    @Test
    void shouldRejectInvalidUSDOTNumber() {
        // Too short, too long, or all zeros.
        assertThrows(IllegalArgumentException.class, () -> USDOTNumber.of("12345"));
        assertThrows(IllegalArgumentException.class, () -> USDOTNumber.of("12345678"));
        assertThrows(IllegalArgumentException.class, () -> USDOTNumber.of("000000"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("123456", USDOTNumber.clean("123-456"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("1234567", USDOTNumber.of("1234567").format());
    }

    @Test
    void shouldIdentifyFormatted() {
        assertTrue(USDOTNumber.isFormatted("123456"));
    }
}
