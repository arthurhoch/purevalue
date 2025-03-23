// File: DNITest.java
package io.github.arthurhoch.purevalue.es;

import io.github.arthurhoch.purevalue.es.dni.DNI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for DNI.
 */
class DNITest {

    @Test
    void shouldAcceptValidDNI() {
        DNI dni = DNI.of("12345678Z");
        assertEquals("12345678Z", dni.value());
    }

    @Test
    void shouldRejectInvalidDNI() {
        assertThrows(IllegalArgumentException.class, () -> DNI.of("12345678A"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("12345678Z", DNI.clean("12345678 Z"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("12345678Z", DNI.of("12345678Z").format());
    }
}
