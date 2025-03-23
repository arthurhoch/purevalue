package io.github.arthurhoch.purevalue.trade;

import io.github.arthurhoch.purevalue.trade.lei.LEI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for LEI.
 */
class LEITest {
    @Test
    void shouldAcceptValidLEI() {
        // Example LEI (hypothetical): "529900T8BM49AURSDO55"
        LEI lei = LEI.of("529900T8BM49AURSDO55");
        assertEquals("529900T8BM49AURSDO55", lei.value());
    }

    @Test
    void shouldRejectInvalidLEI() {
        assertThrows(IllegalArgumentException.class, () -> LEI.of("529900T8BM49AURSDO54"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("529900T8BM49AURSDO55", LEI.of(" 529900t8bm49aursdo55 ").format());
    }
}
