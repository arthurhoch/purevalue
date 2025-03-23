// File: MyKadTest.java
package io.github.arthurhoch.purevalue.my.mykad;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for MyKad.
 */
class MyKadTest {

    @Test
    void shouldAcceptValidMyKad() {
        MyKad mykad = MyKad.of("800101123456");
        assertEquals("800101123456", mykad.value());
    }

    @Test
    void shouldRejectInvalidMyKad() {
        assertThrows(IllegalArgumentException.class, () -> MyKad.of("1234567890123"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("800101123456", MyKad.clean("800101 123456"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("800101123456", MyKad.of("800101123456").format());
    }
}
