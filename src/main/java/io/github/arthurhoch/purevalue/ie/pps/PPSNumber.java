// File: PPSNumber.java
package io.github.arthurhoch.purevalue.ie.pps;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Irish PPS Number.
 */
public record PPSNumber(String value) implements PureValue<String> {
    private static final PPSNumberValidator validator = new PPSNumberValidator();

    public PPSNumber {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid PPS Number: " + value);
        }
    }

    @JsonCreator
    public static PPSNumber fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static PPSNumber of(String value) {
        return new PPSNumber(value);
    }

    public static PPSNumber ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new PPSNumber(value);
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
