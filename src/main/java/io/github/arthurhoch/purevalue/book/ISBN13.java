package io.github.arthurhoch.purevalue.book;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an ISBN-13.
 */
public record ISBN13(String value) implements PureValue<String> {
    private static final ISBN13Validator validator = new ISBN13Validator();

    public ISBN13 {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid ISBN-13: " + value);
        }
    }

    @JsonCreator
    public static ISBN13 fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static ISBN13 of(String value) {
        return new ISBN13(value);
    }

    public static ISBN13 ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new ISBN13(value);
    }

    public static boolean isValid(String value) {
        return validator.isValid(clean(value));
    }

    public static String clean(String value) {
        return validator.clean(value);
    }

    @Override
    public String format() {
        return validator.format(value);
    }
}
