package io.github.arthurhoch.purevalue.trade.eori;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an EORI.
 */
public record EORI(String value) implements PureValue<String> {
    private static final EORIValidator validator = new EORIValidator();

    public EORI {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid EORI: " + value);
        }
    }

    @JsonCreator
    public static EORI fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static EORI of(String value) {
        return new EORI(value);
    }

    public static EORI ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new EORI(value);
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
