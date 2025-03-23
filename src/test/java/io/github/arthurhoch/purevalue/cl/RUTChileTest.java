// File: RUTChileTest.java
package io.github.arthurhoch.purevalue.cl.rut;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for RUTChile.
 */
class RUTChileTest {

    @Test
    void shouldAcceptValidRUTChile() {
        // Example valid RUT: Using body "12345678" calculated check digit is 5; full RUT "123456785"
        RUTChile rut = RUTChile.of("123456785");
        assertEquals("123456785", rut.value());
    }

    @Test
    void shouldRejectInvalidRUTChile() {
        assertThrows(IllegalArgumentException.class, () -> RUTChile.of("12345678K"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("123456785", RUTChile.clean("12.345.678-5"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("123456785", RUTChile.of("123456785").format());
    }
}
