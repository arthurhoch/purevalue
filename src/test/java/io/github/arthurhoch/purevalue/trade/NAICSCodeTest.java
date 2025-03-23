package io.github.arthurhoch.purevalue.trade;

import io.github.arthurhoch.purevalue.trade.naics.NAICSCode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for NAICSCode.
 */
class NAICSCodeTest {
    @Test
    void shouldAcceptValidNAICSCode() {
        NAICSCode code = NAICSCode.of("336111");
        assertEquals("336111", code.value());
    }

    @Test
    void shouldRejectInvalidNAICSCode() {
        assertThrows(IllegalArgumentException.class, () -> NAICSCode.of("33611"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("336111", NAICSCode.of(" 336111 ").format());
    }
}
