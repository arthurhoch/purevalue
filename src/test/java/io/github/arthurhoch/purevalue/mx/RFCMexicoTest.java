// File: RFCMexicoTest.java
package io.github.arthurhoch.purevalue.mx;

import io.github.arthurhoch.purevalue.mx.rfc.RFCMexico;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for RFCMexico.
 */
class RFCMexicoTest {

    @Test
    void shouldAcceptValidRFCIndividual() {
        // Example valid individual RFC: 4 letters + 6 digits + 3 alphanumeric.
        RFCMexico rfc = RFCMexico.of("GOMC800101ABC");
        assertEquals("GOMC800101ABC", rfc.value());
    }

    @Test
    void shouldAcceptValidRFCCompany() {
        // Example valid company RFC: 3 letters + 6 digits + 3 alphanumeric.
        RFCMexico rfc = RFCMexico.of("ABC800101ABC");
        assertEquals("ABC800101ABC", rfc.value());
    }

    @Test
    void shouldRejectInvalidRFC() {
        assertThrows(IllegalArgumentException.class, () -> RFCMexico.of("INVALIDRFC"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("GOMC800101ABC", RFCMexico.clean(" gomc 800101 abc "));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("GOMC800101ABC", RFCMexico.of("GOMC800101ABC").format());
    }
}
