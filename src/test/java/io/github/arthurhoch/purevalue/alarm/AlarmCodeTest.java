package io.github.arthurhoch.purevalue.alarm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlarmCodeTest {
    @Test
    void shouldAcceptValidAlarmCode() {
        AlarmCode code = AlarmCode.of("1234");
        assertEquals("1234", code.value());
        code = AlarmCode.of("987654");
        assertEquals("987654", code.value());
    }

    @Test
    void shouldRejectInvalidAlarmCode() {
        assertThrows(IllegalArgumentException.class, () -> AlarmCode.of("123"));
        assertThrows(IllegalArgumentException.class, () -> AlarmCode.of("1234567"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("1234", AlarmCode.of(" 1234 ").format());
    }
}
