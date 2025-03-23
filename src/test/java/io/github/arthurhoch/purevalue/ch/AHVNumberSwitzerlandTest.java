// File: AHVNumberSwitzerlandTest.java
package io.github.arthurhoch.purevalue.ch;

import io.github.arthurhoch.purevalue.ch.ahv.AHVNumberSwitzerland;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for AHV Number Switzerland.
 */
class AHVNumberSwitzerlandTest {

    @Test
    void shouldAcceptValidAHVNumber() {
        // Valid AHV number: "7561234567893"
        AHVNumberSwitzerland ahv = AHVNumberSwitzerland.of("7561234567893");
        assertEquals("7561234567893", ahv.value());
    }

    @Test
    void shouldRejectInvalidAHVNumber() {
        // "7561234567892" is invalid because the check digit (2) is incorrect (expected is 3)
        assertThrows(IllegalArgumentException.class, () -> AHVNumberSwitzerland.of("7561234567892"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("7561234567893", AHVNumberSwitzerland.clean("756 1234 5678 93"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("7561234567893", AHVNumberSwitzerland.of("7561234567893").format());
    }
}