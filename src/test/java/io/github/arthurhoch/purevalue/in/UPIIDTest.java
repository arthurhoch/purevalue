package io.github.arthurhoch.purevalue.in;

import io.github.arthurhoch.purevalue.in.upi.UPIID;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for UPIID.
 */
class UPIIDTest {
    @Test
    void shouldAcceptValidUPIID() {
        UPIID upi = UPIID.of("user@bank");
        assertEquals("user@bank", upi.value());
    }

    @Test
    void shouldRejectInvalidUPIID() {
        assertThrows(IllegalArgumentException.class, () -> UPIID.of("userbank"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("user@bank", UPIID.of(" user@bank ").format());
    }
}
