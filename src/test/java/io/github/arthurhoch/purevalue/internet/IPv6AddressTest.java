package io.github.arthurhoch.purevalue.internet;

import io.github.arthurhoch.purevalue.internet.ip.IPv6Address;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for IPv6Address.
 */
class IPv6AddressTest {
    @Test
    void shouldAcceptValidIPv6() {
        IPv6Address ip = IPv6Address.of("2001:0db8:85a3:0000:0000:8a2e:0370:7334");
        assertEquals("2001:0db8:85a3:0000:0000:8a2e:0370:7334", ip.value());
    }

    @Test
    void shouldRejectInvalidIPv6() {
        assertThrows(IllegalArgumentException.class, () -> IPv6Address.of("2001:db8:85a3::8a2e:370g:7334"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("2001:0db8:85a3:0000:0000:8a2e:0370:7334", IPv6Address.of(" 2001:0db8:85a3:0000:0000:8a2e:0370:7334 ").format());
    }
}
