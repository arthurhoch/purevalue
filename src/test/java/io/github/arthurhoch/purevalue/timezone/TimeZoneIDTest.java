package io.github.arthurhoch.purevalue.timezone;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TimeZoneIDTest {
    @Test
    void shouldAcceptValidTimeZone() {
        TimeZoneID tz = TimeZoneID.of("America/Sao_Paulo");
        assertEquals("America/Sao_Paulo", tz.value());
    }

    @Test
    void shouldRejectInvalidTimeZone() {
        assertThrows(IllegalArgumentException.class, () -> TimeZoneID.of("Invalid/Zone"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("America/Sao_Paulo", TimeZoneID.of(" America/Sao_Paulo ").format());
    }
}
