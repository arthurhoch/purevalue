// File: VINTest.java
package io.github.arthurhoch.purevalue.vehicle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for VIN.
 */
class VINTest {

    @Test
    void shouldAcceptValidVIN() {
        // Example VIN; check digit is calculated per ISO 3779
        VIN vin = VIN.of("1HGCM82633A004352");
        assertEquals("1HGCM82633A004352", vin.value());
    }

    @Test
    void shouldRejectInvalidVIN() {
        // Invalid VIN with incorrect check digit
        assertThrows(IllegalArgumentException.class, () -> VIN.of("1HGCM82633A004353"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("1HGCM82633A004352", VIN.clean("1HGCM82633A004352"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("1HGCM82633A004352", VIN.of("1HGCM82633A004352").format());
    }

    @Test
    void shouldIdentifyFormatted() {
        assertTrue(VIN.isFormatted("1HGCM82633A004352"));
    }
}
