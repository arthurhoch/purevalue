// File: BSNNetherlandsTest.java
package io.github.arthurhoch.purevalue.nl;

import io.github.arthurhoch.purevalue.nl.bsn.BSNNetherlands;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BSNNetherlands.
 */
class BSNNetherlandsTest {

    @Test
    void shouldAcceptValidBSN() {
        // Example: BSN "123456782" passes the 11-test.
        BSNNetherlands bsn = BSNNetherlands.of("123456782");
        assertEquals("123456782", bsn.value());
    }

    @Test
    void shouldRejectInvalidBSN() {
        assertThrows(IllegalArgumentException.class, () -> BSNNetherlands.of("123456789"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("123456782", BSNNetherlands.of("123456782").format());
    }
}
