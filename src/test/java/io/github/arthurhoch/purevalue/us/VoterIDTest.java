package io.github.arthurhoch.purevalue.us;

import io.github.arthurhoch.purevalue.us.election.VoterID;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for VoterID.
 */
class VoterIDTest {
    @Test
    void shouldAcceptValidVoterID() {
        VoterID vid = VoterID.of("VOTER12345");
        assertEquals("VOTER12345", vid.value());
    }

    @Test
    void shouldRejectInvalidVoterID() {
        assertThrows(IllegalArgumentException.class, () -> VoterID.of("V1"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("VOTER12345", VoterID.of(" voter12345 ").format());
    }
}