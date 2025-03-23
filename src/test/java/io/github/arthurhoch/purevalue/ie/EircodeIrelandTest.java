package io.github.arthurhoch.purevalue.ie;

import io.github.arthurhoch.purevalue.ie.eircode.EircodeIreland;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for EircodeIreland.
 */
class EircodeIrelandTest {
    @Test
    void shouldAcceptValidEircodeWithoutSpace() {
        EircodeIreland eir = EircodeIreland.of("D02Y006");
        assertEquals("D02 Y006", eir.format());
    }

    @Test
    void shouldAcceptValidEircodeWithSpace() {
        EircodeIreland eir = EircodeIreland.of("d02 y006");
        assertEquals("D02 Y006", eir.format());
    }

    @Test
    void shouldRejectInvalidEircode() {
        assertThrows(IllegalArgumentException.class, () -> EircodeIreland.of("D02Y00"));
    }
}
