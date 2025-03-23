package io.github.arthurhoch.purevalue.trade;

import io.github.arthurhoch.purevalue.trade.isin.ISIN;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ISIN.
 */
class ISINTest {

    @Test
    void shouldAcceptValidISIN() {
        // Example: "US0378331005" (Apple Inc. ISIN)
        ISIN isin = ISIN.of("US0378331005");
        assertEquals("US0378331005", isin.value());
    }

    @Test
    void shouldRejectInvalidISIN() {
        assertThrows(IllegalArgumentException.class, () -> ISIN.of("US0378331006"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("US0378331005", ISIN.of(" US0378331005 ").format());
    }
}
