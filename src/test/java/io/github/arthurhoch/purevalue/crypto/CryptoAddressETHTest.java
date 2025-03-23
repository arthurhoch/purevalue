package io.github.arthurhoch.purevalue.crypto;

import io.github.arthurhoch.purevalue.crypto.address.CryptoAddressETH;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CryptoAddressETH.
 */
class CryptoAddressETHTest {
    @Test
    void shouldAcceptValidEthereumAddress() {
        CryptoAddressETH addr = CryptoAddressETH.of("0x52908400098527886E0F7030069857D2E4169EE7");
        assertEquals("0x52908400098527886E0F7030069857D2E4169EE7", addr.value());
    }

    @Test
    void shouldRejectInvalidEthereumAddress() {
        assertThrows(IllegalArgumentException.class, () -> CryptoAddressETH.of("0x123456"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("0x52908400098527886E0F7030069857D2E4169EE7", CryptoAddressETH.of(" 0x52908400098527886E0F7030069857D2E4169EE7 ").format());
    }
}
