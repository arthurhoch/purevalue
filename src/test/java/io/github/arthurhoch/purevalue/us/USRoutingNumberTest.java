// File: USRoutingNumberTest.java
package io.github.arthurhoch.purevalue.us;

import io.github.arthurhoch.purevalue.us.routing.USRoutingNumber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for US Routing Number.
 */
class USRoutingNumberTest {

    @Test
    void shouldAcceptValidRoutingNumber() {
        // Example valid routing number: 011000015
        USRoutingNumber routing = USRoutingNumber.of("011000015");
        assertEquals("011000015", routing.value());
    }

    @Test
    void shouldRejectInvalidRoutingNumber() {
        // Invalid checksum
        assertThrows(IllegalArgumentException.class, () -> USRoutingNumber.of("123456789"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("011000015", USRoutingNumber.clean("011-000015"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("011000015", USRoutingNumber.of("011000015").format());
    }

    @Test
    void shouldIdentifyFormatted() {
        assertTrue(USRoutingNumber.isFormatted("011000015"));
    }
}
