package io.github.arthurhoch.purevalue.internet;

import io.github.arthurhoch.purevalue.internet.bt.BluetoothAddress;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Bluetooth Address.
 */
class BluetoothAddressTest {

    @Test
    void shouldAcceptValidBluetoothAddressWithoutSeparators() {
        BluetoothAddress addr = BluetoothAddress.of("001122AABBCC");
        assertEquals("001122AABBCC", addr.value());
    }

    @Test
    void shouldAcceptValidBluetoothAddressWithColons() {
        BluetoothAddress addr = BluetoothAddress.of("00:11:22:AA:BB:CC");
        assertEquals("001122AABBCC", addr.value());
    }

    @Test
    void shouldRejectInvalidBluetoothAddress() {
        assertThrows(IllegalArgumentException.class, () -> BluetoothAddress.of("001122AABB"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("001122AABBCC", BluetoothAddress.of("00-11-22-AA-BB-CC").format());
    }
}
