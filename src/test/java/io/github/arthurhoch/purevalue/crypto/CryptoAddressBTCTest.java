package io.github.arthurhoch.purevalue.crypto;

import io.github.arthurhoch.purevalue.crypto.address.CryptoAddressBTC;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CryptoAddressBTC.
 */
class CryptoAddressBTCTest {

    @Test
    void shouldAcceptValidLegacyAddress() {
        CryptoAddressBTC addr = CryptoAddressBTC.of("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");
        assertEquals("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", addr.value());
    }

    @Test
    void shouldAcceptValidBech32Address() {
        CryptoAddressBTC addr = CryptoAddressBTC.of("bc1qw508d6qejxtdg4y5r3zarvary0c5xw7kygt080");
        assertEquals("bc1qw508d6qejxtdg4y5r3zarvary0c5xw7kygt080", addr.value());
    }

    @Test
    void shouldRejectInvalidBitcoinAddress() {
        assertThrows(IllegalArgumentException.class, () -> CryptoAddressBTC.of("invalidaddress"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", CryptoAddressBTC.of(" 1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa ").format());
    }
}
