package io.github.arthurhoch.purevalue.br;

import io.github.arthurhoch.purevalue.br.cfop.CFOPBrazil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CFOPBrazil.
 */
class CFOPBrazilTest {
    @Test
    void shouldAcceptValidCFOPBrazil() {
        CFOPBrazil cfop = CFOPBrazil.of("5102");
        assertEquals("5102", cfop.value());
    }

    @Test
    void shouldRejectInvalidCFOPBrazil() {
        assertThrows(IllegalArgumentException.class, () -> CFOPBrazil.of("510"));
        assertThrows(IllegalArgumentException.class, () -> CFOPBrazil.of("51020"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("5102", CFOPBrazil.of(" 5102 ").format());
    }
}
