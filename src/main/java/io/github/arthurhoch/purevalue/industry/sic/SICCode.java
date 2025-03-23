package io.github.arthurhoch.purevalue.industry.sic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a SIC Code.
 */
public record SICCode(String value) implements PureValue<String> {
    private static final SICCodeValidator validator = new SICCodeValidator();

    public SICCode {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid SIC Code: " + value);
        }
    }

    @JsonCreator
    public static SICCode fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static SICCode of(String value) {
        return new SICCode(value);
    }

    public static SICCode ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new SICCode(value);
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
