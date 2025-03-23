// File: TaxIDGermanyTest.java
package io.github.arthurhoch.purevalue.de;

import io.github.arthurhoch.purevalue.de.taxid.TaxIDGermany;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for German Tax ID.
 */
class TaxIDGermanyTest {

    @Test
    void shouldAcceptValidTaxID() {
        // Hypothetical valid Tax ID (11 digits) with a correct check digit.
        TaxIDGermany taxID = TaxIDGermany.of("65012345670");
        assertEquals("65012345670", taxID.value());
    }

    @Test
    void shouldRejectInvalidTaxID() {
        // Invalid because the check digit is incorrect.
        assertThrows(IllegalArgumentException.class, () -> TaxIDGermany.of("65012345671"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("65012345670", TaxIDGermany.clean("650-123-45670"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("65012345670", TaxIDGermany.of("65012345670").format());
    }
}
