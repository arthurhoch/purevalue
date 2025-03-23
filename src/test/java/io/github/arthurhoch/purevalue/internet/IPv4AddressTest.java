package io.github.arthurhoch.purevalue.internet;

import io.github.arthurhoch.purevalue.internet.ip.IPv4Address;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for IPv4Address.
 */
class IPv4AddressTest {
    @Test
    void shouldAcceptValidIPv4() {
        IPv4Address ip = IPv4Address.of("192.168.1.1");
        assertEquals("192.168.1.1", ip.value());
    }

    @Test
    void shouldRejectInvalidIPv4() {
        assertThrows(IllegalArgumentException.class, () -> IPv4Address.of("256.100.100.100"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("192.168.1.1", IPv4Address.of(" 192.168.1.1 ").format());
    }
}
