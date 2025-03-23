package io.github.arthurhoch.purevalue.software.uuid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a UUID Version 5.
 */
public record UUIDVersion5(String value) implements PureValue<String> {
    private static final UUIDVersion5Validator validator = new UUIDVersion5Validator();

    public UUIDVersion5 {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid UUID Version 5: " + value);
        }
    }

    @JsonCreator
    public static UUIDVersion5 fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static UUIDVersion5 of(String value) {
        return new UUIDVersion5(value);
    }

    public static UUIDVersion5 ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new UUIDVersion5(value);
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
