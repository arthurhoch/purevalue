package io.github.arthurhoch.purevalue.container;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ContainerCodeISO.
 */
class ContainerCodeISOTest {

    @Test
    void shouldAcceptValidContainerCodeISO() {
        // Using valid check digit '5' based on our calculation.
        ContainerCodeISO code = ContainerCodeISO.of("MSCU1234565");
        assertEquals("MSCU1234565", code.value());
    }

    @Test
    void shouldRejectInvalidContainerCodeISO() {
        // Change check digit should fail.
        assertThrows(IllegalArgumentException.class, () -> ContainerCodeISO.of("MSCU1234561"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("MSCU1234565", ContainerCodeISO.of(" MSCU 1234565 ").format());
    }
}
