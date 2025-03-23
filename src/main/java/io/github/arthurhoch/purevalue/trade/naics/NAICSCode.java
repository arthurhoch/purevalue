package io.github.arthurhoch.purevalue.trade.naics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a NAICS Code.
 */
public record NAICSCode(String value) implements PureValue<String> {
    private static final NAICSCodeValidator validator = new NAICSCodeValidator();

    public NAICSCode {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid NAICS Code: " + value);
        }
    }

    @JsonCreator
    public static NAICSCode fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static NAICSCode of(String value) {
        return new NAICSCode(value);
    }

    public static NAICSCode ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new NAICSCode(value);
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
