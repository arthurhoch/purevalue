// File: LicensePlate.java
package io.github.arthurhoch.purevalue.vehicle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a generic vehicle License Plate.
 */
public record LicensePlate(String value) implements PureValue<String> {
    private static final LicensePlateValidator validator = new LicensePlateValidator();

    public LicensePlate {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid License Plate: " + value);
        }
    }

    @JsonCreator
    public static LicensePlate fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static LicensePlate of(String value) {
        return new LicensePlate(value);
    }

    public static LicensePlate ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new LicensePlate(value);
    }

    public static boolean isValid(String value) {
        return validator.isValid(clean(value));
    }

    public static boolean isFormatted(String value) {
        return validator.isFormatted(value);
    }

    public static String clean(String value) {
        return validator.clean(value);
    }

    @Override
    public String format() {
        return validator.format(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
