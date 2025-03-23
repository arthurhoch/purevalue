package io.github.arthurhoch.purevalue.fr;

import io.github.arthurhoch.purevalue.fr.siren.SIREN;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SIREN.
 */
class SIRENTest {
    @Test
    void shouldAcceptValidSIREN() {
        // Example SIREN "732829320" is known valid.
        SIREN siren = SIREN.of("732829320");
        assertEquals("732829320", siren.value());
    }

    @Test
    void shouldRejectInvalidSIREN() {
        assertThrows(IllegalArgumentException.class, () -> SIREN.of("732829321"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("732829320", SIREN.of("732 829 320").format());
    }
}
