package io.github.arthurhoch.purevalue.internet.cidr;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CIDRBlock.
 */
class CIDRBlockTest {
    @Test
    void shouldAcceptValidCIDRBlock() {
        CIDRBlock cidr = CIDRBlock.of("192.168.0.0/24");
        assertEquals("192.168.0.0/24", cidr.value());
    }

    @Test
    void shouldRejectInvalidCIDRBlock() {
        assertThrows(IllegalArgumentException.class, () -> CIDRBlock.of("192.168.0.0/33"));
        assertThrows(IllegalArgumentException.class, () -> CIDRBlock.of("999.168.0.0/24"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("192.168.0.0/24", CIDRBlock.of(" 192.168.0.0/24 ").format());
    }
}
