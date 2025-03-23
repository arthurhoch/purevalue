package io.github.arthurhoch.purevalue.br;

import io.github.arthurhoch.purevalue.br.ibge.IBGECodeBrazil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IBGECodeBrazilTest {
    @Test
    void shouldAcceptValidIBGECode() {
        IBGECodeBrazil ibge = IBGECodeBrazil.of("3550308");
        assertEquals("3550308", ibge.value());
    }

    @Test
    void shouldRejectInvalidIBGECode() {
        assertThrows(IllegalArgumentException.class, () -> IBGECodeBrazil.of("355030"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("3550308", IBGECodeBrazil.of(" 3550308 ").format());
    }
}
