package io.github.arthurhoch.purevalue.uk;

import io.github.arthurhoch.purevalue.uk.sortcode.SortCodeUK;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SortCodeUK.
 */
class SortCodeUKTest {
    @Test
    void shouldAcceptValidSortCode() {
        SortCodeUK sortCode = SortCodeUK.of("123456");
        assertEquals("123456", sortCode.value());
    }

    @Test
    void shouldRejectInvalidSortCode() {
        assertThrows(IllegalArgumentException.class, () -> SortCodeUK.of("12345"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("12-34-56", SortCodeUK.of("123456").format());
    }
}
