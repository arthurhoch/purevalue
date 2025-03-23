// File: VIN.java
package io.github.arthurhoch.purevalue.vehicle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Vehicle Identification Number (VIN).
 */
public record VIN(String value) implements PureValue<String> {
    private static final VINValidator validator = new VINValidator();

    public VIN {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid VIN: " + value);
        }
    }

    @JsonCreator
    public static VIN fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static VIN of(String value) {
        return new VIN(value);
    }

    public static VIN ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new VIN(value);
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
