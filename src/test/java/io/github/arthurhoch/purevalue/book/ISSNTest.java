package io.github.arthurhoch.purevalue.book;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ISSN.
 */
class ISSNTest {
    @Test
    void shouldAcceptValidISSN() {
        ISSN issn = ISSN.of("03785955"); // Known valid ISSN: 0378-5955
        assertEquals("03785955", issn.value());
    }

    @Test
    void shouldRejectInvalidISSN() {
        assertThrows(IllegalArgumentException.class, () -> ISSN.of("03785954"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("03785955", ISSN.of("0378-5955").format());
    }
}
