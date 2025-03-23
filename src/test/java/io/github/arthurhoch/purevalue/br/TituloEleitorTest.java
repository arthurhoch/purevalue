package io.github.arthurhoch.purevalue.br;

import io.github.arthurhoch.purevalue.br.titulo.TituloEleitor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TituloEleitorTest {

    @Test
    void shouldAcceptValidTitulo() {
        TituloEleitor t = TituloEleitor.of("287143270194");
        assertEquals("287143270194", t.value());
    }

    @Test
    void shouldRejectInvalidTitulo() {
        assertThrows(IllegalArgumentException.class, () -> TituloEleitor.of("123456789000"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("123456789012", TituloEleitor.clean("1234 5678 9012"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("2871 4327 0194", TituloEleitor.of("287143270194").format());
    }

    @Test
    void shouldIdentifyFormatted() {
        assertTrue(TituloEleitor.isFormatted("1234 5678 9012"));
    }
}
