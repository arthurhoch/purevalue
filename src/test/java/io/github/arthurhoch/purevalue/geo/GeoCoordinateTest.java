package io.github.arthurhoch.purevalue.geo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GeoCoordinateTest {

    @Test
    void shouldAcceptValidCoordinates() {
        GeoCoordinate coord = new GeoCoordinate(45.0, -73.5);
        assertEquals("45.0,-73.5", coord.value());
    }

    @Test
    void shouldRejectInvalidLatitude() {
        assertThrows(IllegalArgumentException.class, () -> new GeoCoordinate(-100.0, 0));
    }

    @Test
    void shouldRejectInvalidLongitude() {
        assertThrows(IllegalArgumentException.class, () -> new GeoCoordinate(0, 200.0));
    }

    @Test
    void shouldParseFromJson() {
        GeoCoordinate coord = GeoCoordinate.fromJson("45.0, -73.5");
        assertEquals(45.0, coord.latitude());
        assertEquals(-73.5, coord.longitude());
    }

    @Test
    void shouldFormatCorrectly() {
        GeoCoordinate coord = new GeoCoordinate(45.123, -73.456);
        assertEquals("45.123,-73.456", coord.format());
    }
}
