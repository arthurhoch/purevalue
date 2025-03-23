package io.github.arthurhoch.purevalue.internet.meid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a MEID.
 */
public record MEID(String value) implements PureValue<String> {
    private static final MEIDValidator validator = new MEIDValidator();

    public MEID {
        value = clean(value);
        if(!isValid(value)) {
            throw new IllegalArgumentException("Invalid MEID: " + value);
        }
    }

    @JsonCreator
    public static MEID fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static MEID of(String value) {
        return new MEID(value);
    }

    public static MEID ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new MEID(value);
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
