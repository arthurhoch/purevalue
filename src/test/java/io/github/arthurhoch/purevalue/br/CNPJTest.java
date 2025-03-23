package io.github.arthurhoch.purevalue.br;

import io.github.arthurhoch.purevalue.br.cnpj.CNPJ;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CNPJTest {

    @Test
    void shouldAcceptValidCNPJ() {
        CNPJ cnpj = CNPJ.of("11.222.333/0001-81");
        assertEquals("11222333000181", cnpj.value());
    }

    @Test
    void shouldRejectInvalidCNPJ() {
        assertThrows(IllegalArgumentException.class, () -> CNPJ.of("00.000.000/0000-00"));
    }

    @Test
    void shouldCleanInputCorrectly() {
        assertEquals("11222333000181", CNPJ.clean("11.222.333/0001-81"));
    }

    @Test
    void shouldFormatCorrectly() {
        CNPJ cnpj = CNPJ.of("11222333000181");
        assertEquals("11.222.333/0001-81", cnpj.format());
    }

    @Test
    void shouldIdentifyFormattedCNPJ() {
        assertTrue(CNPJ.isFormatted("11.222.333/0001-81"));
        assertFalse(CNPJ.isFormatted("11222333000181"));
    }
}
