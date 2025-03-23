// File: TFNTest.java
package io.github.arthurhoch.purevalue.au;

import io.github.arthurhoch.purevalue.au.tfn.TFN;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for TFN.
 */
class TFNTest {

    @Test
    void shouldAcceptValidTFN_9Digits() {
        // Valid 9-digit TFN: "541872933"
        TFN tfn = TFN.of("541872933");
        assertEquals("541872933", tfn.value());
    }

    @Test
    void shouldAcceptValidTFN_8Digits() {
        // Valid 8-digit TFN: "87254314"
        TFN tfn = TFN.of("87254314");
        assertEquals("87254314", tfn.value());
    }

    @Test
    void shouldRejectInvalidTFN() {
        assertThrows(IllegalArgumentException.class, () -> TFN.of("123456789"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("541872936", TFN.clean(" 541 872 936 "));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("541872933", TFN.of("541872933").format());
    }
}
