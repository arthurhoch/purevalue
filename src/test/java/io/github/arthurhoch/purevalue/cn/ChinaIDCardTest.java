// File: ChinaIDCardTest.java
package io.github.arthurhoch.purevalue.cn.idcard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ChinaIDCard.
 */
class ChinaIDCardTest {

    @Test
    void shouldAcceptValidChinaIDCard() {
        // Example valid China ID Card (18 characters).
        ChinaIDCard idCard = ChinaIDCard.of("11010519491231002X");
        assertEquals("11010519491231002X", idCard.value());
    }

    @Test
    void shouldRejectInvalidChinaIDCard() {
        assertThrows(IllegalArgumentException.class, () -> ChinaIDCard.of("110105194912310021"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("11010519491231002X", ChinaIDCard.clean("110105 19491231 002X"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("11010519491231002X", ChinaIDCard.of("11010519491231002X").format());
    }
}
