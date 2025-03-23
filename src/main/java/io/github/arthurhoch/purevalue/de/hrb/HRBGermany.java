package io.github.arthurhoch.purevalue.de.hrb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing German HRB (Handelsregister) number.
 */
public record HRBGermany(String value) implements PureValue<String> {
    private static final HRBGermanyValidator validator = new HRBGermanyValidator();

    public HRBGermany {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid HRB Germany: " + value);
        }
    }

    @JsonCreator
    public static HRBGermany fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static HRBGermany of(String value) {
        return new HRBGermany(value);
    }

    public static HRBGermany ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new HRBGermany(value);
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
