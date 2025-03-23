package io.github.arthurhoch.purevalue.jp.postcode;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Japanese postal code.
 */
public record PostcodeJapan(String value) implements PureValue<String> {
    private static final PostcodeJapanValidator validator = new PostcodeJapanValidator();

    public PostcodeJapan {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid PostcodeJapan: " + value);
        }
    }

    @JsonCreator
    public static PostcodeJapan fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static PostcodeJapan of(String value) {
        return new PostcodeJapan(value);
    }

    public static PostcodeJapan ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new PostcodeJapan(value);
    }

    public static boolean isValid(String value) {
        return validator.isValid(value);
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
