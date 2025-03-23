package io.github.arthurhoch.purevalue.br.boleto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BoletoBrazil.
 */
class BoletoBrazilTest {
    @Test
    void shouldAcceptValidBoleto47() {
        // Example 47-digit boleto
        BoletoBrazil boleto = BoletoBrazil.of("00190500954014481606906809350314337370000000100");
        assertEquals("00190500954014481606906809350314337370000000100", boleto.value());
    }

    @Test
    void shouldAcceptValidBoleto48() {
        // Example 48-digit boleto (hypothetical)
        BoletoBrazil boleto = BoletoBrazil.of("001905009540144816069068093503143373700000001001");
        assertEquals("001905009540144816069068093503143373700000001001", boleto.value());
    }

    @Test
    void shouldRejectInvalidBoleto() {
        assertThrows(IllegalArgumentException.class, () -> BoletoBrazil.of("0019050095401448"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("00190500954014481606906809350314337370000000100",
                BoletoBrazil.of("00190500954014481606906809350314337370000000100").format());
    }
}
