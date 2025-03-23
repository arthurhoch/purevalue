package io.github.arthurhoch.purevalue.finance.iban;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an IBAN.
 */
public record IBAN(String value) implements PureValue<String> {
    private static final IBANValidator validator = new IBANValidator();

    public IBAN {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid IBAN: " + value);
        }
    }

    @JsonCreator
    public static IBAN fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static IBAN of(String value) {
        return new IBAN(value);
    }

    public static IBAN ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new IBAN(value);
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
