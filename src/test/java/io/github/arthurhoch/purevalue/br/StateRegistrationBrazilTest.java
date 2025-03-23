package io.github.arthurhoch.purevalue.br;

import io.github.arthurhoch.purevalue.br.state.StateRegistrationBrazil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StateRegistrationBrazilTest {

    @Test
    void shouldAcceptValidStateRegistration() {
        assertDoesNotThrow(() -> StateRegistrationBrazil.of("123456789012"));
    }

    @Test
    void shouldRejectInvalidLength() {
        assertThrows(IllegalArgumentException.class, () -> StateRegistrationBrazil.of("123"));
    }

    @Test
    void shouldCleanInputCorrectly() {
        assertEquals("123456789012", StateRegistrationBrazil.clean("123.456.789-012"));
    }
}
