// File: IqamaNumberTest.java
package io.github.arthurhoch.purevalue.sa;

import io.github.arthurhoch.purevalue.sa.iqama.IqamaNumber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for IqamaNumber.
 */
class IqamaNumberTest {
    @Test
    void shouldAcceptValidIqamaNumber() {
        IqamaNumber iqama = IqamaNumber.of("1234567890");
        assertEquals("1234567890", iqama.value());
    }

    @Test
    void shouldRejectInvalidIqamaNumber() {
        assertThrows(IllegalArgumentException.class, () -> IqamaNumber.of("123456789"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("1234567890", IqamaNumber.clean("123-456-7890"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("1234567890", IqamaNumber.of("1234567890").format());
    }
}
