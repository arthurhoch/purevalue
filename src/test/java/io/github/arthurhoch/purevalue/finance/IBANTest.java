package io.github.arthurhoch.purevalue.finance;

import io.github.arthurhoch.purevalue.finance.iban.IBAN;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for IBAN.
 */
class IBANTest {

    @Test
    void shouldAcceptValidIBAN() {
        // Example: German IBAN "DE89370400440532013000"
        IBAN iban = IBAN.of("DE89 3704 0044 0532 0130 00");
        assertEquals("DE89370400440532013000", iban.value());
    }

    @Test
    void shouldRejectInvalidIBAN() {
        assertThrows(IllegalArgumentException.class, () -> IBAN.of("DE89 3704 0044 0532 0130 01"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("DE89370400440532013000", IBAN.of("DE89 3704 0044 0532 0130 00").format());
    }
}
