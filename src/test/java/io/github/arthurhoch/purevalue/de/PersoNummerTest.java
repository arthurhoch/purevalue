// File: PersoNummerTest.java
package io.github.arthurhoch.purevalue.de;

import io.github.arthurhoch.purevalue.de.personum.PersoNummer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PersoNummer.
 */
class PersoNummerTest {

    @Test
    void shouldAcceptValidPersoNummer() {
        // "A1B2C3D4E4" is valid if the computed check digit equals 4.
        PersoNummer pn = PersoNummer.of("A1B2C3D4E4");
        assertEquals("A1B2C3D4E4", pn.value());
    }

    @Test
    void shouldRejectInvalidPersoNummer() {
        // Invalid check digit.
        assertThrows(IllegalArgumentException.class, () -> PersoNummer.of("A1B2C3D4E5"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("A1B2C3D4E4", PersoNummer.clean("A1B2-C3D4 E4"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("A1B2C3D4E4", PersoNummer.of("A1B2C3D4E4").format());
    }
}
