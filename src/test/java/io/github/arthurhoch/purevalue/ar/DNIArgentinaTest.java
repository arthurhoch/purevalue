// File: DNIArgentinaTest.java
package io.github.arthurhoch.purevalue.ar;

import io.github.arthurhoch.purevalue.ar.dni.DNIArgentina;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for DNIArgentina.
 */
class DNIArgentinaTest {

    @Test
    void shouldAcceptValidDNI_8Digits() {
        DNIArgentina dni = DNIArgentina.of("12345678");
        assertEquals("12345678", dni.value());
    }

    @Test
    void shouldAcceptValidDNI_7Digits() {
        DNIArgentina dni = DNIArgentina.of("1234567");
        assertEquals("1234567", dni.value());
    }

    @Test
    void shouldRejectInvalidDNI() {
        assertThrows(IllegalArgumentException.class, () -> DNIArgentina.of("0000000"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("12345678", DNIArgentina.clean("12 345678"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("12345678", DNIArgentina.of("12345678").format());
    }
}
