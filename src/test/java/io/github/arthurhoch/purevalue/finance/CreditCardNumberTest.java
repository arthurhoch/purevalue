package io.github.arthurhoch.purevalue.finance;

import io.github.arthurhoch.purevalue.finance.credit.CreditCardNumber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CreditCardNumber.
 */
class CreditCardNumberTest {

    @Test
    void shouldAcceptValidCreditCardNumber() {
        // Example Visa test number (valid per Luhn): 4111111111111111
        CreditCardNumber card = CreditCardNumber.of("4111 1111 1111 1111");
        assertEquals("4111111111111111", card.value());
    }

    @Test
    void shouldRejectInvalidCreditCardNumber() {
        assertThrows(IllegalArgumentException.class, () -> CreditCardNumber.of("4111111111111112"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("4111111111111111", CreditCardNumber.of("4111-1111-1111-1111").format());
    }
}
