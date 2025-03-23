package io.github.arthurhoch.purevalue.payment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PaymentTransactionID.
 */
class PaymentTransactionIDTest {

    @Test
    void shouldAcceptValidPaymentTransactionID() {
        PaymentTransactionID id = PaymentTransactionID.of("PAYPAL-TRX_1234567890");
        assertEquals("PAYPAL-TRX_1234567890", id.value());
    }

    @Test
    void shouldRejectInvalidPaymentTransactionID() {
        // Too short
        assertThrows(IllegalArgumentException.class, () -> PaymentTransactionID.of("abc123"));
        // Contains invalid characters (e.g., spaces)
        assertThrows(IllegalArgumentException.class, () -> PaymentTransactionID.of("INVALID TRX"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("STRIPE-ABC123", PaymentTransactionID.of(" stripe-abc123 ").format());
    }
}
