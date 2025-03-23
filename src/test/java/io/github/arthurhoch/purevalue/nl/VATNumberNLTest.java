package io.github.arthurhoch.purevalue.nl;

import io.github.arthurhoch.purevalue.nl.vat.VATNumberNL;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for VATNumberNL.
 */
class VATNumberNLTest {
    @Test
    void shouldAcceptValidVATNumberNL() {
        VATNumberNL vat = VATNumberNL.of("NL123456789B01");
        assertEquals("NL123456789B01", vat.value());
    }

    @Test
    void shouldRejectInvalidVATNumberNL() {
        assertThrows(IllegalArgumentException.class, () -> VATNumberNL.of("NL12345678B01"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("NL123456789B01", VATNumberNL.of("nl123456789b01").format());
    }
}
