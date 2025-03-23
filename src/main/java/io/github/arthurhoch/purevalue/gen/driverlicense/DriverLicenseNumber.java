package io.github.arthurhoch.purevalue.gen.driverlicense;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Driver License Number.
 */
public record DriverLicenseNumber(String value) implements PureValue<String> {
    private static final DriverLicenseNumberValidator validator = new DriverLicenseNumberValidator();

    public DriverLicenseNumber {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Driver License Number: " + value);
        }
    }

    @JsonCreator
    public static DriverLicenseNumber fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static DriverLicenseNumber of(String value) {
        return new DriverLicenseNumber(value);
    }

    public static DriverLicenseNumber ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new DriverLicenseNumber(value);
    }

    public static boolean isValid(String value) {
        return validator.isValid(value);
    }

    public static String clean(String value) {
        return validator.clean(value);
    }

    @Override
    public String format() {
        return validator.format(value);
    }
}
