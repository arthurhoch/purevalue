package io.github.arthurhoch.purevalue.jp;

import io.github.arthurhoch.purevalue.jp.postcode.PostcodeJapan;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PostcodeJapan.
 */
class PostcodeJapanTest {

    @Test
    void shouldAcceptValidRawPostcode() {
        PostcodeJapan pc = PostcodeJapan.of("1234567");
        // Internal value is raw digits; formatting returns "123-4567"
        assertEquals("1234567", pc.value());
        assertEquals("123-4567", pc.format());
    }

    @Test
    void shouldAcceptValidFormattedPostcode() {
        PostcodeJapan pc = PostcodeJapan.of("123-4567");
        assertEquals("1234567", pc.value());
        assertEquals("123-4567", pc.format());
    }

    @Test
    void shouldRejectInvalidPostcode() {
        assertThrows(IllegalArgumentException.class, () -> PostcodeJapan.of("123-456"));
        assertThrows(IllegalArgumentException.class, () -> PostcodeJapan.of("123456"));
    }
}
