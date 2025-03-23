// File: PersonalNumberSwedenTest.java
package io.github.arthurhoch.purevalue.se;

import io.github.arthurhoch.purevalue.se.personnummer.PersonalNumberSweden;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Personal Number Sweden.
 */
class PersonalNumberSwedenTest {

    @Test
    void shouldAcceptValidPersonalNumberSweden() {
        // "8507099801" is valid; the computed check digit is 1.
        PersonalNumberSweden pnum = PersonalNumberSweden.of("8507099801");
        assertEquals("8507099801", pnum.value());
    }

    @Test
    void shouldRejectInvalidPersonalNumberSweden() {
        // "8507099805" is invalid because the check digit is incorrect.
        assertThrows(IllegalArgumentException.class, () -> PersonalNumberSweden.of("8507099805"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("8507099801", PersonalNumberSweden.clean("850 709 9801"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("8507099801", PersonalNumberSweden.of("8507099801").format());
    }
}
