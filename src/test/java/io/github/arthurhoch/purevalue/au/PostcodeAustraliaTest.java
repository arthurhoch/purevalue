package io.github.arthurhoch.purevalue.au;

import io.github.arthurhoch.purevalue.au.postal.PostcodeAustralia;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PostcodeAustralia.
 */
class PostcodeAustraliaTest {
    @Test
    void shouldAcceptValidPostcode() {
        PostcodeAustralia pc = PostcodeAustralia.of("2000");
        assertEquals("2000", pc.value());
    }

    @Test
    void shouldRejectInvalidPostcode() {
        assertThrows(IllegalArgumentException.class, () -> PostcodeAustralia.of("200"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("2000", PostcodeAustralia.of(" 2000 ").format());
    }
}
