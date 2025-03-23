package io.github.arthurhoch.purevalue.internet.email;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an Email address.
 */
public record Email(String value) implements PureValue<String> {
    private static final EmailValidator validator = new EmailValidator();

    public Email {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Email: " + value);
        }
    }

    @JsonCreator
    public static Email fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static Email of(String value) {
        return new Email(value);
    }

    public static Email ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new Email(value);
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
