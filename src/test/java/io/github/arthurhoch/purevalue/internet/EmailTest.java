package io.github.arthurhoch.purevalue.internet;

import io.github.arthurhoch.purevalue.internet.email.Email;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Email.
 */
class EmailTest {

    @Test
    void shouldAcceptValidEmail() {
        Email email = Email.of("user@example.com");
        assertEquals("user@example.com", email.value());
    }

    @Test
    void shouldRejectInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> Email.of("user@@example.com"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("user@example.com", Email.of("  user@example.com  ").format());
    }
}
