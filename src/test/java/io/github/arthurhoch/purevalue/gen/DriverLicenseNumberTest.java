package io.github.arthurhoch.purevalue.gen;

import io.github.arthurhoch.purevalue.gen.driverlicense.DriverLicenseNumber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for DriverLicenseNumber.
 */
class DriverLicenseNumberTest {
    @Test
    void shouldAcceptValidDriverLicenseNumber() {
        DriverLicenseNumber dln = DriverLicenseNumber.of("DL12345");
        assertEquals("DL12345", dln.value());
    }

    @Test
    void shouldRejectInvalidDriverLicenseNumber() {
        assertThrows(IllegalArgumentException.class, () -> DriverLicenseNumber.of("DL12"));
        assertThrows(IllegalArgumentException.class, () -> DriverLicenseNumber.of("DL1234567890123456"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("DL12345", DriverLicenseNumber.of(" dl 12345 ").format());
    }
}
