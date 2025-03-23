package io.github.arthurhoch.purevalue.internet.url;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a URL.
 */
public record URLValue(String value) implements PureValue<String> {
    private static final URLValueValidator validator = new URLValueValidator();

    public URLValue {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid URL: " + value);
        }
    }

    @JsonCreator
    public static URLValue fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static URLValue of(String value) {
        return new URLValue(value);
    }

    public static URLValue ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new URLValue(value);
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
