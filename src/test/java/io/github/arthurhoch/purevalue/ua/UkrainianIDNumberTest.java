// File: UkrainianIDNumberTest.java
package io.github.arthurhoch.purevalue.ua;

import io.github.arthurhoch.purevalue.ua.uid.UkrainianIDNumber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Ukrainian ID Number.
 */
class UkrainianIDNumberTest {

    @Test
    void shouldAcceptValidUkrainianIDNumber() {
        UkrainianIDNumber uid = UkrainianIDNumber.of("1234567890");
        assertEquals("1234567890", uid.value());
    }

    @Test
    void shouldRejectInvalidUkrainianIDNumber() {
        assertThrows(IllegalArgumentException.class, () -> UkrainianIDNumber.of("0000000000"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("1234567890", UkrainianIDNumber.of("1234567890").format());
    }
}
