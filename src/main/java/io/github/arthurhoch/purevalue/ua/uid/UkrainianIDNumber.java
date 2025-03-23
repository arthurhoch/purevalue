// File: UkrainianIDNumber.java
package io.github.arthurhoch.purevalue.ua.uid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Ukrainian ID Number.
 */
public record UkrainianIDNumber(String value) implements PureValue<String> {
    private static final UkrainianIDNumberValidator validator = new UkrainianIDNumberValidator();

    public UkrainianIDNumber {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Ukrainian ID Number: " + value);
        }
    }

    @JsonCreator
    public static UkrainianIDNumber fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static UkrainianIDNumber of(String value) {
        return new UkrainianIDNumber(value);
    }

    public static UkrainianIDNumber ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new UkrainianIDNumber(value);
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
