// File: TFN.java
package io.github.arthurhoch.purevalue.au.tfn;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Australian TFN.
 */
public record TFN(String value) implements PureValue<String> {
    private static final TFNValidator validator = new TFNValidator();

    public TFN {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid TFN: " + value);
        }
    }

    @JsonCreator
    public static TFN fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static TFN of(String value) {
        return new TFN(value);
    }

    public static TFN ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new TFN(value);
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
