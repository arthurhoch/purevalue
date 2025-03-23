package io.github.arthurhoch.purevalue.book;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an ISBN-10.
 */
public record ISBN10(String value) implements PureValue<String> {
    private static final ISBN10Validator validator = new ISBN10Validator();

    public ISBN10 {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid ISBN-10: " + value);
        }
    }

    @JsonCreator
    public static ISBN10 fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static ISBN10 of(String value) {
        return new ISBN10(value);
    }

    public static ISBN10 ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new ISBN10(value);
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
