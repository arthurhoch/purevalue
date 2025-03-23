// File: ARCNumberTest.java
package io.github.arthurhoch.purevalue.tw.arc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ARCNumber.
 */
class ARCNumberTest {

    @Test
    void shouldAcceptValidARCNumber() {
        ARCNumber arc = ARCNumber.of("A123456789");
        assertEquals("A123456789", arc.value());
    }

    @Test
    void shouldRejectInvalidARCNumber() {
        assertThrows(IllegalArgumentException.class, () -> ARCNumber.of("1234567890"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("A123456789", ARCNumber.clean(" a123456789 "));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("A123456789", ARCNumber.of("A123456789").format());
    }
}
