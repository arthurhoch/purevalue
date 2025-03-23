package io.github.arthurhoch.purevalue.br;

import io.github.arthurhoch.purevalue.br.cep.CEPBrazil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CEPBrazilTest {

    @Test
    void shouldAcceptValidCEP() {
        CEPBrazil cep = CEPBrazil.of("12345-678");
        assertEquals("12345678", cep.value());
    }

    @Test
    void shouldRejectInvalidCEP() {
        assertThrows(IllegalArgumentException.class, () -> CEPBrazil.of("00000-000"));
    }

    @Test
    void shouldFormatCorrectly() {
        CEPBrazil cep = CEPBrazil.of("12345678");
        assertEquals("12345-678", cep.format());
    }

    @Test
    void shouldIdentifyFormattedCEP() {
        assertTrue(CEPBrazil.isFormatted("12345-678"));
        assertFalse(CEPBrazil.isFormatted("12345678"));
    }
}
