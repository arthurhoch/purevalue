package io.github.arthurhoch.purevalue.es;

import io.github.arthurhoch.purevalue.es.nie.NIE;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for NIE.
 */
class NIETest {

    @Test
    void shouldAcceptValidNIE() {
        NIE nie = NIE.of("X1234567L");
        assertEquals("X1234567L", nie.value());
    }

    @Test
    void shouldRejectInvalidNIE() {
        assertThrows(IllegalArgumentException.class, () -> NIE.of("X1234567M"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("X1234567L", NIE.clean("X 1234567 L"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("X1234567L", NIE.of("X1234567L").format());
    }
}