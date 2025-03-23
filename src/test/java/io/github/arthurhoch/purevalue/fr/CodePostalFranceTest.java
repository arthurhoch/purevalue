package io.github.arthurhoch.purevalue.fr;

import io.github.arthurhoch.purevalue.fr.postal.CodePostalFrance;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CodePostalFrance.
 */
class CodePostalFranceTest {

    @Test
    void shouldAcceptValidCodePostal() {
        CodePostalFrance cp = CodePostalFrance.of("75008");
        assertEquals("75008", cp.value());
    }

    @Test
    void shouldRejectInvalidCodePostal() {
        assertThrows(IllegalArgumentException.class, () -> CodePostalFrance.of("7500"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("75008", CodePostalFrance.of(" 75008 ").format());
    }
}
