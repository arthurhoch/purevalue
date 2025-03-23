// File: EmiratesIDTest.java
package io.github.arthurhoch.purevalue.ae;

import io.github.arthurhoch.purevalue.ae.emiratesid.EmiratesID;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Emirates ID.
 */
class EmiratesIDTest {
    @Test
    void shouldAcceptValidEmiratesID() {
        EmiratesID id = EmiratesID.of("123456789012345");
        assertEquals("123456789012345", id.value());
    }

    @Test
    void shouldRejectInvalidEmiratesID() {
        assertThrows(IllegalArgumentException.class, () -> EmiratesID.of("12345678901234"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("123456789012345", EmiratesID.clean("1234 5678 9012 345"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("123456789012345", EmiratesID.of("123456789012345").format());
    }
}
