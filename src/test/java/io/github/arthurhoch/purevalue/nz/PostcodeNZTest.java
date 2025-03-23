package io.github.arthurhoch.purevalue.nz;

import io.github.arthurhoch.purevalue.nz.postal.PostcodeNZ;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PostcodeNZ.
 */
class PostcodeNZTest {
    @Test
    void shouldAcceptValidPostcode() {
        PostcodeNZ nz = PostcodeNZ.of("6011");
        assertEquals("6011", nz.value());
    }

    @Test
    void shouldRejectInvalidPostcode() {
        assertThrows(IllegalArgumentException.class, () -> PostcodeNZ.of("601"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("6011", PostcodeNZ.of(" 6011 ").format());
    }
}
