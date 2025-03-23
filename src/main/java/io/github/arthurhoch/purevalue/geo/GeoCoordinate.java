package io.github.arthurhoch.purevalue.geo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a geographic coordinate (latitude and longitude).
 * This implementation validates that latitude is between -90 and +90,
 * and longitude is between -180 and +180.
 * The canonical string representation is "latitude,longitude".
 */
public record GeoCoordinate(double latitude, double longitude) implements PureValue<String> {

    public GeoCoordinate {
        if (!GeoCoordinateValidator.isValid(latitude, longitude)) {
            throw new IllegalArgumentException("Invalid GeoCoordinate: latitude=" + latitude + ", longitude=" + longitude);
        }
    }

    /**
     * Creates a GeoCoordinate from a string in the format "latitude,longitude".
     */
    @JsonCreator
    public static GeoCoordinate fromJson(String value) {
        double[] parts = GeoCoordinateValidator.parse(value);
        return new GeoCoordinate(parts[0], parts[1]);
    }

    /**
     * Returns the canonical string representation: "latitude,longitude".
     */
    @JsonValue
    public String toJson() {
        return format();
    }

    /**
     * Factory method using a string representation.
     */
    public static GeoCoordinate of(String value) {
        return fromJson(value);
    }

    /**
     * Factory method using latitude and longitude.
     */
    public static GeoCoordinate of(double latitude, double longitude) {
        return new GeoCoordinate(latitude, longitude);
    }

    /**
     * Returns true if the given string can be parsed into a valid GeoCoordinate.
     */
    public static boolean isValid(String value) {
        try {
            double[] parts = GeoCoordinateValidator.parse(value);
            return GeoCoordinateValidator.isValid(parts[0], parts[1]);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Cleans the input string (trimming).
     */
    public static String clean(String value) {
        return (value == null) ? null : value.trim();
    }

    /**
     * Returns the canonical string representation: "latitude,longitude".
     */
    @Override
    public String format() {
        return GeoCoordinateValidator.format(latitude, longitude);
    }

    /**
     * Returns the canonical string value.
     */
    @Override
    public String value() {
        return format();
    }
}
