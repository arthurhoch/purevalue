package io.github.arthurhoch.purevalue.be;

import io.github.arthurhoch.purevalue.be.vat.VATNumberBelgium;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for VATNumberBelgium.
 */
class VATNumberBelgiumTest {
    @Test
    void shouldAcceptValidVATNumberBelgium() {
        // Using base "12345714" (8 digits) yields remainder = 39, so expected check = 97 - 39 = 58.
        VATNumberBelgium vat = VATNumberBelgium.of("1234571458");
        assertEquals("1234571458", vat.value());
    }

    @Test
    void shouldRejectInvalidVATNumberBelgium() {
        // Changing the check digits (e.g., using "57" instead of "58") should be rejected.
        assertThrows(IllegalArgumentException.class, () -> VATNumberBelgium.of("1234571457"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("1234571458", VATNumberBelgium.of("123 457 14 58").format());
    }
}