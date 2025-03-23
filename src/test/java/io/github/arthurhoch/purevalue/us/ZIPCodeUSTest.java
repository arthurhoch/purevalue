package io.github.arthurhoch.purevalue.us.zip;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for US ZIP Code.
 */
class ZIPCodeUSTest {
    @Test
    void shouldAcceptValid5DigitZIP() {
        ZIPCodeUS zip = ZIPCodeUS.of("12345");
        assertEquals("12345", zip.value());
    }

    @Test
    void shouldAcceptValid9DigitZIP() {
        ZIPCodeUS zip = ZIPCodeUS.of("123456789");
        assertEquals("123456789", zip.value());
    }

    @Test
    void shouldAcceptFormattedZIPWithHyphen() {
        ZIPCodeUS zip = ZIPCodeUS.of("12345-6789");
        assertEquals("123456789", zip.value());
    }

    @Test
    void shouldRejectInvalidZIP() {
        assertThrows(IllegalArgumentException.class, () -> ZIPCodeUS.of("1234"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("123456789", ZIPCodeUS.of("12345-6789").format());
    }
}
