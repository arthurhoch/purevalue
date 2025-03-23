// File: CUITArgentinaTest.java
package io.github.arthurhoch.purevalue.ar;

import io.github.arthurhoch.purevalue.ar.cuit.CUITArgentina;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CUITArgentina.
 */
class CUITArgentinaTest {

    @Test
    void shouldAcceptValidCUIT() {
        // Example valid CUIT: "20329642330" (must satisfy the check digit algorithm).
        CUITArgentina cuit = CUITArgentina.of("20329642330");
        assertEquals("20329642330", cuit.value());
    }

    @Test
    void shouldRejectInvalidCUIT() {
        assertThrows(IllegalArgumentException.class, () -> CUITArgentina.of("20329642331"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("20329642330", CUITArgentina.clean("20-32964233-0"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("20329642330", CUITArgentina.of("20329642330").format());
    }
}
