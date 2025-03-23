// File: SIN.java
package io.github.arthurhoch.purevalue.ca.sin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Canadian Social Insurance Number (SIN).
 */
public record SIN(String value) implements PureValue<String> {
    private static final SINValidator validator = new SINValidator();

    public SIN {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid SIN: " + value);
        }
    }

    @JsonCreator
    public static SIN fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static SIN of(String value) {
        return new SIN(value);
    }

    public static SIN ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new SIN(value);
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
}
