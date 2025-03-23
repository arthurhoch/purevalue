package io.github.arthurhoch.purevalue.phone;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an international phone number in E.164 format.
 */
public record PhoneNumber(String value) implements PureValue<String> {
    private static final PhoneNumberValidator validator = new PhoneNumberValidator();

    public PhoneNumber {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Phone Number (E.164): " + value);
        }
    }

    @JsonCreator
    public static PhoneNumber fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static PhoneNumber of(String value) {
        return new PhoneNumber(value);
    }

    public static PhoneNumber ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new PhoneNumber(value);
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
