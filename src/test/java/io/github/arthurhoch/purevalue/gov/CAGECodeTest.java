package io.github.arthurhoch.purevalue.gov;

import io.github.arthurhoch.purevalue.gov.cage.CAGECode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CAGECode.
 */
class CAGECodeTest {
    @Test
    void shouldAcceptValidCAGECode() {
        CAGECode cage = CAGECode.of("123AB");
        assertEquals("123AB", cage.value());
    }

    @Test
    void shouldRejectInvalidCAGECode() {
        assertThrows(IllegalArgumentException.class, () -> CAGECode.of("12AB"));
        assertThrows(IllegalArgumentException.class, () -> CAGECode.of("123ABC"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("123AB", CAGECode.of(" 123ab ").format());
    }
}
