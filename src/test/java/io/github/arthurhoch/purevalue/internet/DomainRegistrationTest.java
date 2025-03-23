package io.github.arthurhoch.purevalue.internet;

import io.github.arthurhoch.purevalue.internet.domain.DomainRegistration;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for DomainRegistration.
 */
class DomainRegistrationTest {
    @Test
    void shouldAcceptValidDomain() {
        DomainRegistration domain = DomainRegistration.of("example.com");
        assertEquals("example.com", domain.value());
    }

    @Test
    void shouldRejectInvalidDomain() {
        assertThrows(IllegalArgumentException.class, () -> DomainRegistration.of("example..com"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("example.com", DomainRegistration.of(" EXAMPLE.COM ").format());
    }
}
