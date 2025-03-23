// File: EuropeanLicensePlateTest.java
package io.github.arthurhoch.purevalue.eu;

import io.github.arthurhoch.purevalue.eu.licenseplate.EuropeanLicensePlate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for European License Plate.
 */
class EuropeanLicensePlateTest {

    @Test
    void shouldAcceptValidEuropeanLicensePlate() {
        EuropeanLicensePlate plate = EuropeanLicensePlate.of("AB-123-CD");
        assertEquals("AB123CD", plate.value());
    }

    @Test
    void shouldRejectInvalidEuropeanLicensePlate() {
        // Too short
        assertThrows(IllegalArgumentException.class, () -> EuropeanLicensePlate.of("AB1"));
        // Too long
        assertThrows(IllegalArgumentException.class, () -> EuropeanLicensePlate.of("ABCDEFGHIJKLM"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("AB123CD", EuropeanLicensePlate.clean("AB-123-CD"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("AB123CD", EuropeanLicensePlate.of("AB 123 CD").format());
    }

    @Test
    void shouldIdentifyFormatted() {
        assertTrue(EuropeanLicensePlate.isFormatted("AB123CD"));
    }
}
