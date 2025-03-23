package io.github.arthurhoch.purevalue.jo.nationalid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Jordan National ID.
 */
public record JordanNationalID(String value) implements PureValue<String> {
    private static final JordanNationalIDValidator validator = new JordanNationalIDValidator();

    public JordanNationalID {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Jordan National ID: " + value);
        }
    }

    @JsonCreator
    public static JordanNationalID fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static JordanNationalID of(String value) {
        return new JordanNationalID(value);
    }

    public static JordanNationalID ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new JordanNationalID(value);
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
