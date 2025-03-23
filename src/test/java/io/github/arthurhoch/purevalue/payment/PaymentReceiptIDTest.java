package io.github.arthurhoch.purevalue.payment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PaymentReceiptID.
 */
class PaymentReceiptIDTest {

    @Test
    void shouldAcceptValidPaymentReceiptID() {
        PaymentReceiptID id = PaymentReceiptID.of("PAYREC123456");
        assertEquals("PAYREC123456", id.value());
    }

    @Test
    void shouldRejectInvalidPaymentReceiptID() {
        // Too short
        assertThrows(IllegalArgumentException.class, () -> PaymentReceiptID.of("ABC123"));
        // Contains spaces (which are trimmed and converted, so spaces cause a mismatch with pattern)
        assertThrows(IllegalArgumentException.class, () -> PaymentReceiptID.of("INVALID RECEIPT"));
    }

    @Test
    void shouldFormatCorrectly() {
        // Input with lower-case and extra spaces will be cleaned to uppercase.
        assertEquals("PAYREC123456", PaymentReceiptID.of(" payrec123456 ").format());
    }
}
