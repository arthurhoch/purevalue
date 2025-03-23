package io.github.arthurhoch.purevalue.br;

import io.github.arthurhoch.purevalue.br.pispasep.PISPASEP;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PISPASEPTest {

    @Test
    void shouldAcceptValidPISPASEP() {
        PISPASEP pis = PISPASEP.of("768.42709.82-7"); // valid according to algorithm
        assertEquals("76842709827", pis.value());
        assertEquals("768.42709.82-7", pis.format());
    }

    @Test
    void shouldRejectInvalidPISPASEP() {
        assertThrows(IllegalArgumentException.class, () -> PISPASEP.of("02345678900"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("575.83184.63-8", PISPASEP.of("575.83184.63-8").format());
    }

    @Test
    void shouldCleanProperly() {
        assertEquals("12345678108", PISPASEP.clean("123.45678.10-8"));
    }

    @Test
    void shouldDetectFormattedInput() {
        assertTrue(PISPASEP.isFormatted("123.45678.10-8"));
        assertFalse(PISPASEP.isFormatted("12345678108"));
    }
}
