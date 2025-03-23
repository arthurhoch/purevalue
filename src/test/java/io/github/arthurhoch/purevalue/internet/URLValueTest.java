package io.github.arthurhoch.purevalue.internet.url;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for URLValue.
 */
class URLValueTest {
    @Test
    void shouldAcceptValidURL() {
        URLValue url = URLValue.of("https://www.example.com");
        assertEquals("https://www.example.com", url.value());
    }

    @Test
    void shouldRejectInvalidURL() {
        assertThrows(IllegalArgumentException.class, () -> URLValue.of("htp:/invalid-url"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("https://www.example.com", URLValue.of(" https://www.example.com ").format());
    }
}
