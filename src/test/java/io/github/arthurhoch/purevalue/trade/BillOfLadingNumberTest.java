package io.github.arthurhoch.purevalue.trade;

import io.github.arthurhoch.purevalue.trade.bl.BillOfLadingNumber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BillOfLadingNumber.
 */
class BillOfLadingNumberTest {

    @Test
    void shouldAcceptValidBillOfLadingNumber() {
        // Example: "BL-12345678" (10 characters including dash)
        BillOfLadingNumber bol = BillOfLadingNumber.of("BL-12345678");
        assertEquals("BL-12345678", bol.value());
    }

    @Test
    void shouldRejectInvalidBillOfLadingNumber() {
        // Too short (less than 8 characters)
        assertThrows(IllegalArgumentException.class, () -> BillOfLadingNumber.of("BL-1234"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("BL-12345678", BillOfLadingNumber.of(" bl-12345678 ").format());
    }
}
