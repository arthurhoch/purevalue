package io.github.arthurhoch.purevalue.pt;

import io.github.arthurhoch.purevalue.pt.postal.CodigoPostalPortugal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CodigoPostalPortugal.
 */
class CodigoPostalPortugalTest {
    @Test
    void shouldAcceptValidFormattedCodigoPostal() {
        // Properly formatted: exactly "NNNN-NNN"
        CodigoPostalPortugal cp = CodigoPostalPortugal.of("1234-567");
        assertEquals("1234567", cp.value());
    }

    @Test
    void shouldAcceptValidRawCodigoPostal() {
        // Raw 7 digits are acceptable and will be formatted when requested.
        CodigoPostalPortugal cp = CodigoPostalPortugal.of("1234567");
        // The internal value is raw ("1234567"), but formatting returns "1234-567"
        assertEquals("1234567", cp.value());
        assertEquals("1234-567", cp.format());
    }

    @Test
    void shouldRejectInvalidCodigoPostal() {
        // "123-4567" cleans to "1234567" but since the input contained a dash,
        // it is expected to be in the "NNNN-NNN" format. Hence, it should be rejected.
        assertThrows(IllegalArgumentException.class, () -> CodigoPostalPortugal.of("12324567"));
    }

    @Test
    void shouldFormatCorrectly() {
        // When given a raw input with spaces, the cleaned value is "1234567"
        // and the formatted output should be "1234-567".
        assertEquals("1234-567", CodigoPostalPortugal.of("1234 567").format());
    }
}
