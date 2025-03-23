package io.github.arthurhoch.purevalue.jo;

import io.github.arthurhoch.purevalue.jo.nationalid.JordanNationalID;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Jordan National ID.
 */
class JordanNationalIDTest {
    @Test
    void shouldAcceptValidJordanNationalID() {
        JordanNationalID id = JordanNationalID.of("1234567890");
        assertEquals("1234567890", id.value());
    }

    @Test
    void shouldRejectInvalidJordanNationalID() {
        assertThrows(IllegalArgumentException.class, () -> JordanNationalID.of("123456789"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("1234567890", JordanNationalID.of("123-456-7890").format());
    }
}
