// File: USDOTNumber.java
package io.github.arthurhoch.purevalue.us.vehicle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a US USDOT Number.
 */
public record USDOTNumber(String value) implements PureValue<String> {
    private static final USDOTNumberValidator validator = new USDOTNumberValidator();

    public USDOTNumber {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid USDOT Number: " + value);
        }
    }

    @JsonCreator
    public static USDOTNumber fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static USDOTNumber of(String value) {
        return new USDOTNumber(value);
    }

    public static USDOTNumber ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new USDOTNumber(value);
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
