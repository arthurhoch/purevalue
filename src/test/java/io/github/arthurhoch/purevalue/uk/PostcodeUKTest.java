package io.github.arthurhoch.purevalue.uk;

import io.github.arthurhoch.purevalue.uk.postcode.PostcodeUK;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for UK Postcode.
 */
class PostcodeUKTest {
    @Test
    void shouldAcceptValidPostcode() {
        PostcodeUK postcode = PostcodeUK.of("EC1A 1BB");
        assertEquals("EC1A 1BB", postcode.value());
    }

    @Test
    void shouldRejectInvalidPostcode() {
        assertThrows(IllegalArgumentException.class, () -> PostcodeUK.of("INVALID"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("EC1A 1BB", PostcodeUK.of(" ec1a 1bb ").format());
    }
}
