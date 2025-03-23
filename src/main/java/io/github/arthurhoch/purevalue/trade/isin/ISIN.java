package io.github.arthurhoch.purevalue.trade.isin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an ISIN.
 */
public record ISIN(String value) implements PureValue<String> {
    private static final ISINValidator validator = new ISINValidator();

    public ISIN {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid ISIN: " + value);
        }
    }

    @JsonCreator
    public static ISIN fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static ISIN of(String value) {
        return new ISIN(value);
    }

    public static ISIN ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new ISIN(value);
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
