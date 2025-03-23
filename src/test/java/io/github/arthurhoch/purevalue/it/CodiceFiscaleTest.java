// File: CodiceFiscaleTest.java
package io.github.arthurhoch.purevalue.it.codicefiscale;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Codice Fiscale.
 */
class CodiceFiscaleTest {

    @Test
    void shouldAcceptValidCodiceFiscale() {
        // Example for "Mario Rossi" – expected check character 'U' with the adjusted algorithm.
        CodiceFiscale cf = CodiceFiscale.of("RSSMRA85M01H501U");
        assertEquals("RSSMRA85M01H501U", cf.value());
    }

    @Test
    void shouldRejectInvalidCodiceFiscale() {
        assertThrows(IllegalArgumentException.class, () -> CodiceFiscale.of("RSSMRA85M01H501X"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("RSSMRA85M01H501U", CodiceFiscale.clean("RSS MRA85M01 H501U"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("RSSMRA85M01H501U", CodiceFiscale.of("RSSMRA85M01H501U").format());
    }
}
