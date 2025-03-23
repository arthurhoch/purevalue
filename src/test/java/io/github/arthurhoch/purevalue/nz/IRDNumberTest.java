// File: IRDNumberTest.java
package io.github.arthurhoch.purevalue.nz;

import io.github.arthurhoch.purevalue.nz.ird.IRDNumber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for IRD Number.
 */
class IRDNumberTest {

    @Test
    void shouldAcceptValidIRDNumber_9Digits() {
        // Válido: zero à esquerda, equivalente a 8 dígitos
        IRDNumber ird = IRDNumber.of("75563901");
        assertEquals("75563901", ird.value());
    }

    @Test
    void shouldAcceptValidIRDNumber_8Digits() {
        IRDNumber ird = IRDNumber.of("35178155");
        assertEquals("35178155", ird.value());
    }

    @Test
    void shouldRejectInvalidIRDNumber() {
        // Inválido: check digit errado
        assertThrows(IllegalArgumentException.class, () -> IRDNumber.of("12345678"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("59459174", IRDNumber.of("59459174").format());
    }
}

