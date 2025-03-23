package io.github.arthurhoch.purevalue.br;

import io.github.arthurhoch.purevalue.br.cnh.CNH;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CNHTest {

    @Test
    void shouldAcceptValidCNH() {
        CNH cnh = CNH.of("78979869158");
        assertEquals("78979869158", cnh.value());
    }

    @Test
    void shouldRejectInvalidCNH() {
        assertThrows(IllegalArgumentException.class, () -> CNH.of("12345678912"));
        assertThrows(IllegalArgumentException.class, () -> CNH.of("00000000000"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("39762156050", CNH.clean("39762156050"));
    }

    @Test
    void shouldIdentifyFormatted() {
        assertTrue(CNH.isFormatted("39762156050"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("62158487806", CNH.of("62158487806").format());
    }
}
