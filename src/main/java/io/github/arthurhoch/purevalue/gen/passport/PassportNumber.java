package io.github.arthurhoch.purevalue.gen.passport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Passport Number.
 */
public record PassportNumber(String value) implements PureValue<String> {
    private static final PassportNumberValidator validator = new PassportNumberValidator();

    public PassportNumber {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Passport Number: " + value);
        }
    }

    @JsonCreator
    public static PassportNumber fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static PassportNumber of(String value) {
        return new PassportNumber(value);
    }

    public static PassportNumber ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new PassportNumber(value);
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
