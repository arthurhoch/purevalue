package io.github.arthurhoch.purevalue.in;

import io.github.arthurhoch.purevalue.in.pincode.PinCodeIndia;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PinCodeIndia.
 */
class PinCodeIndiaTest {
    @Test
    void shouldAcceptValidPinCode() {
        PinCodeIndia pin = PinCodeIndia.of("400001");
        assertEquals("400001", pin.value());
    }

    @Test
    void shouldRejectInvalidPinCode() {
        assertThrows(IllegalArgumentException.class, () -> PinCodeIndia.of("40001"));
        assertThrows(IllegalArgumentException.class, () -> PinCodeIndia.of("4000011"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("400001", PinCodeIndia.of(" 400001 ").format());
    }
}
