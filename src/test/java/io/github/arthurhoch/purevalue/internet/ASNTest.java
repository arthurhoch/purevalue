package io.github.arthurhoch.purevalue.internet;

import io.github.arthurhoch.purevalue.internet.asn.ASN;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ASN.
 */
class ASNTest {
    @Test
    void shouldAcceptValidASN() {
        ASN asn = ASN.of("64512");
        assertEquals("64512", asn.value());

        ASN asn2 = ASN.of("4294967295");
        assertEquals("4294967295", asn2.value());
    }

    @Test
    void shouldRejectInvalidASN() {
        assertThrows(IllegalArgumentException.class, () -> ASN.of("4294967296")); // too high
        assertThrows(IllegalArgumentException.class, () -> ASN.of("abc123"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("64512", ASN.of(" 64512 ").format());
    }
}
