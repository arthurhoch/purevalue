// File: SSNTest.java
package io.github.arthurhoch.purevalue.us;

import io.github.arthurhoch.purevalue.us.ssn.SSN;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SSN.
 */
class SSNTest {

    @Test
    void shouldAcceptValidSSN() {
        SSN ssn = SSN.of("123-45-6789");
        assertEquals("123456789", ssn.value());
    }

    @Test
    void shouldRejectInvalidSSN() {
        assertThrows(IllegalArgumentException.class, () -> SSN.of("000-00-0000"));
        assertThrows(IllegalArgumentException.class, () -> SSN.of("666-12-3456"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("123456789", SSN.clean("123-45-6789"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("123-45-6789", SSN.of("123456789").format());
    }

    @Test
    void shouldIdentifyFormatted() {
        assertTrue(SSN.isFormatted("123-45-6789"));
    }
}
