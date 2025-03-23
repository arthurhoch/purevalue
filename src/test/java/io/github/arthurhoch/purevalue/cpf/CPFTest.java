package io.github.arthurhoch.purevalue.cpf;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CPFTest {

    @Test
    void shouldAcceptValidCPF() {
        CPF cpf = CPF.of("111.444.777-35");
        assertEquals("11144477735", cpf.value());
    }

    @Test
    void shouldRejectInvalidCPF() {
        assertThrows(IllegalArgumentException.class, () -> CPF.of("123.456.789-00"));
    }

    @Test
    void shouldReturnNullForNullInput() {
        assertNull(CPF.ofNullable(null));
    }

    @Test
    void shouldReturnNullForBlankInput() {
        assertNull(CPF.ofNullable("  "));
    }

    @Test
    void shouldCleanInputCorrectly() {
        assertEquals("11144477735", CPF.clean("111.444.777-35"));
    }

    @Test
    void shouldFormatCorrectly() {
        CPF cpf = CPF.of("11144477735");
        assertEquals("111.444.777-35", cpf.format());
    }

    @Test
    void shouldIdentifyFormattedCPF() {
        assertTrue(CPF.isFormatted("111.444.777-35"));
        assertFalse(CPF.isFormatted("11144477735"));
    }

    @Test
    void shouldBeEqual() {
        assertEquals(CPF.of("111.444.777-35"), CPF.of("11144477735"));
    }
}
