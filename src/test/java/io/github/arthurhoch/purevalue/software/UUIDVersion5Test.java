package io.github.arthurhoch.purevalue.software;

import io.github.arthurhoch.purevalue.software.uuid.UUIDVersion5;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for UUIDVersion5.
 */
class UUIDVersion5Test {

    @Test
    void shouldAcceptValidUUIDVersion5() {
        // Example valid UUID version 5:
        String uuid = "123e4567-e89b-5d3a-a456-426614174000";
        UUIDVersion5 uuidV5 = UUIDVersion5.of(uuid);
        assertEquals(uuid.toLowerCase(), uuidV5.value());
    }

    @Test
    void shouldRejectInvalidUUIDVersion5() {
        // Wrong version number (e.g., version 4)
        String invalidUuid = "123e4567-e89b-4d3a-a456-426614174000";
        assertThrows(IllegalArgumentException.class, () -> UUIDVersion5.of(invalidUuid));
    }

    @Test
    void shouldFormatCorrectly() {
        String uuid = "123E4567-E89B-5D3A-A456-426614174000";
        assertEquals("123e4567-e89b-5d3a-a456-426614174000", UUIDVersion5.of(uuid).format());
    }
}
