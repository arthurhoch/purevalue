package io.github.arthurhoch.purevalue.ca;

import io.github.arthurhoch.purevalue.ca.business.BusinessNumberCanada;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Business Number Canada.
 */
class BusinessNumberCanadaTest {
    @Test
    void shouldAcceptValidBusinessNumber() {
        BusinessNumberCanada bn = BusinessNumberCanada.of("123456789");
        assertEquals("123456789", bn.value());
    }

    @Test
    void shouldRejectInvalidBusinessNumber() {
        assertThrows(IllegalArgumentException.class, () -> BusinessNumberCanada.of("12345678"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("123456789", BusinessNumberCanada.of("123 456 789").format());
    }
}
