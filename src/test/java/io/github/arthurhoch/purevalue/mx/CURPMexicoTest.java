// File: CURPMexicoTest.java
package io.github.arthurhoch.purevalue.mx;

import io.github.arthurhoch.purevalue.mx.curp.CURPMexico;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CURPMexico.
 */
class CURPMexicoTest {

    @Test
    void shouldAcceptValidCURP() {
        // Hypothetical valid CURP:
        // "GOMC800101HDFABC09" where:
        // - GOMC: 4 letters,
        // - 800101: birth date,
        // - H: gender,
        // - DF: valid state code,
        // - ABC: 3 letters,
        // - 0: homoclave,
        // - 9: check digit.
        CURPMexico curp = CURPMexico.of("GOMC800101HDFABC09");
        assertEquals("GOMC800101HDFABC09", curp.value());
    }

    @Test
    void shouldRejectInvalidCURP() {
        assertThrows(IllegalArgumentException.class, () -> CURPMexico.of("INVALIDCURP123456"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("GOMC800101HDFABC09", CURPMexico.clean(" gomc 800101 hdf abc09 "));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("GOMC800101HDFABC09", CURPMexico.of("GOMC800101HDFABC09").format());
    }
}
