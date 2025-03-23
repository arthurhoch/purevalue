// File: CUILArgentinaTest.java
package io.github.arthurhoch.purevalue.ar;

import io.github.arthurhoch.purevalue.ar.cuil.CUILArgentina;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CUILArgentina.
 */
class CUILArgentinaTest {

    @Test
    void shouldAcceptValidCUIL() {
        // Example valid CUIL: "27123456780" (must satisfy the check digit algorithm).
        CUILArgentina cuil = CUILArgentina.of("27123456780");
        assertEquals("27123456780", cuil.value());
    }

    @Test
    void shouldRejectInvalidCUIL() {
        assertThrows(IllegalArgumentException.class, () -> CUILArgentina.of("27123456781"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("27123456780", CUILArgentina.clean("27-1234567-80"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("27123456780", CUILArgentina.of("27123456780").format());
    }
}
