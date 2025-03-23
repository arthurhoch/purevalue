// File: IRDNumber.java
package io.github.arthurhoch.purevalue.nz.ird;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing New Zealand IRD Number.
 */
public record IRDNumber(String value) implements PureValue<String> {
    private static final IRDNumberValidator validator = new IRDNumberValidator();

    public IRDNumber {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid IRD Number: " + value);
        }
    }

    @JsonCreator
    public static IRDNumber fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static IRDNumber of(String value) {
        return new IRDNumber(value);
    }

    public static IRDNumber ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new IRDNumber(value);
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
