package io.github.arthurhoch.purevalue.internet;

import io.github.arthurhoch.purevalue.internet.mac.MACAddress;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for MACAddress.
 */
class MACAddressTest {
    @Test
    void shouldAcceptValidMACAddressWithColons() {
        MACAddress mac = MACAddress.of("00:1A:2B:3C:4D:5E");
        assertEquals("001A2B3C4D5E", mac.value());
    }

    @Test
    void shouldAcceptValidMACAddressWithDashes() {
        MACAddress mac = MACAddress.of("00-1A-2B-3C-4D-5E");
        assertEquals("001A2B3C4D5E", mac.value());
    }

    @Test
    void shouldAcceptValidMACAddressWithoutSeparators() {
        MACAddress mac = MACAddress.of("001A2B3C4D5E");
        assertEquals("001A2B3C4D5E", mac.value());
    }

    @Test
    void shouldRejectInvalidMACAddress() {
        assertThrows(IllegalArgumentException.class, () -> MACAddress.of("001A2B3C4D"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("001A2B3C4D5E", MACAddress.of("00:1a:2b:3c:4d:5e").format());
    }
}
