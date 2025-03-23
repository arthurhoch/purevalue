package io.github.arthurhoch.purevalue.de;

import io.github.arthurhoch.purevalue.de.plz.PLZGermany;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PLZGermany.
 */
class PLZGermanyTest {

    @Test
    void shouldAcceptValidPLZ() {
        PLZGermany plz = PLZGermany.of("10115");
        assertEquals("10115", plz.value());
    }

    @Test
    void shouldRejectInvalidPLZ() {
        assertThrows(IllegalArgumentException.class, () -> PLZGermany.of("1011"));
        assertThrows(IllegalArgumentException.class, () -> PLZGermany.of("101155"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("10115", PLZGermany.of(" 10115 ").format());
    }
}
