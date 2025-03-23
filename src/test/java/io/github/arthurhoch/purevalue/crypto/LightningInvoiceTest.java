package io.github.arthurhoch.purevalue.crypto;

import io.github.arthurhoch.purevalue.crypto.lightning.LightningInvoice;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for LightningInvoice.
 */
class LightningInvoiceTest {

    @Test
    void shouldAcceptValidLightningInvoice() {
        LightningInvoice invoice = LightningInvoice.of("lnbc2500u1psxywpppabcdef1234567890");
        assertEquals("lnbc2500u1psxywpppabcdef1234567890", invoice.value());
    }

    @Test
    void shouldRejectInvalidLightningInvoice() {
        assertThrows(IllegalArgumentException.class, () -> LightningInvoice.of("invalidinvoice123"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("lntb1000abc123", LightningInvoice.of(" LNTB1000ABC123 ").format());
    }
}
