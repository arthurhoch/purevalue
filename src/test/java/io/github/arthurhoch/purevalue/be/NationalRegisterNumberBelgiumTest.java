// File: NationalRegisterNumberBelgiumTest.java
package io.github.arthurhoch.purevalue.be;

import io.github.arthurhoch.purevalue.be.nrn.NationalRegisterNumberBelgium;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for National Register Number Belgium.
 */
class NationalRegisterNumberBelgiumTest {
    @Test
    void shouldAcceptValidNationalRegisterNumberBelgium() {
        // For base "123456789":
        // 123456789 mod 97 = 39, so expected check = 97 - 39 = 58.
        NationalRegisterNumberBelgium nrn = NationalRegisterNumberBelgium.of("12345678958");
        assertEquals("12345678958", nrn.value());
    }

    @Test
    void shouldRejectInvalidNationalRegisterNumberBelgium() {
        assertThrows(IllegalArgumentException.class, () -> NationalRegisterNumberBelgium.of("12345678953"));
    }

    @Test
    void shouldCleanCorrectly() {
        assertEquals("12345678952", NationalRegisterNumberBelgium.clean("123.456.789-52"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("12345678958", NationalRegisterNumberBelgium.of("12345678958").format());
    }
}
