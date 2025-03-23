// File: NIRTest.java
package io.github.arthurhoch.purevalue.fr;

import io.github.arthurhoch.purevalue.fr.nir.NIR;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for NIR.
 */
class NIRTest {

    @Test
    void shouldAcceptValidNIR() {
        // Using a valid NIR:
        // Base = "2750935012345" → 2750935012345 mod 97 = 43 → Expected key = 97 - 43 = 54.
        // Full NIR = "275093501234554" (15 digits).
        NIR nir = NIR.of("275093501234554");
        assertEquals("275093501234554", nir.value());
    }

    @Test
    void shouldRejectInvalidNIR() {
        // Invalid due to wrong control key.
        assertThrows(IllegalArgumentException.class, () -> NIR.of("27509350123452"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("27509350123451", NIR.clean("27 509 350 123451"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("275093501234554", NIR.of("275093501234554").format());
    }
}
