// File: MyNumberJapanTest.java
package io.github.arthurhoch.purevalue.jp.mynumber;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for MyNumberJapan.
 */
class MyNumberJapanTest {

    @Test
    void shouldAcceptValidMyNumberJapan() {
        // Valid MyNumberJapan: first 11 digits "12345678901" yield a check digit '4'
        MyNumberJapan myNumber = MyNumberJapan.of("123456789014");
        assertEquals("123456789014", myNumber.value());
    }

    @Test
    void shouldRejectInvalidMyNumberJapan() {
        // Change the check digit to trigger rejection.
        assertThrows(IllegalArgumentException.class, () -> MyNumberJapan.of("123456789015"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("123456789014", MyNumberJapan.clean("123 456 789 014"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("123456789014", MyNumberJapan.of("123456789014").format());
    }
}
