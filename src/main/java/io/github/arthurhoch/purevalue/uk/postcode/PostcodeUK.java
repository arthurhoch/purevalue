package io.github.arthurhoch.purevalue.uk.postcode;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a UK Postcode.
 */
public record PostcodeUK(String value) implements PureValue<String> {
    private static final PostcodeUKValidator validator = new PostcodeUKValidator();

    public PostcodeUK {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid UK Postcode: " + value);
        }
    }

    @JsonCreator
    public static PostcodeUK fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static PostcodeUK of(String value) {
        return new PostcodeUK(value);
    }

    public static PostcodeUK ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new PostcodeUK(value);
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
