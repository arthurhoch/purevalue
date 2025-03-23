// File: LicensePlateTest.java
package io.github.arthurhoch.purevalue.vehicle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for License Plate.
 */
class LicensePlateTest {

    @Test
    void shouldAcceptValidLicensePlate() {
        LicensePlate plate = LicensePlate.of("ABC-1234");
        assertEquals("ABC1234", plate.value());
    }

    @Test
    void shouldRejectInvalidLicensePlate() {
        // Too short
        assertThrows(IllegalArgumentException.class, () -> LicensePlate.of("A"));
        // Too long
        assertThrows(IllegalArgumentException.class, () -> LicensePlate.of("ABCDEFGHIJKL"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("ABC1234", LicensePlate.clean("ABC-1234"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("ABC1234", LicensePlate.of("ABC 1234").format());
    }

    @Test
    void shouldIdentifyFormatted() {
        assertTrue(LicensePlate.isFormatted("ABC1234"));
    }
}
