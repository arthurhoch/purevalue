package io.github.arthurhoch.purevalue.nl.vat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Dutch VAT Number.
 */
public record VATNumberNL(String value) implements PureValue<String> {
    private static final VATNumberNLValidator validator = new VATNumberNLValidator();

    public VATNumberNL {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid VAT Number NL: " + value);
        }
    }

    @JsonCreator
    public static VATNumberNL fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static VATNumberNL of(String value) {
        return new VATNumberNL(value);
    }

    public static VATNumberNL ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new VATNumberNL(value);
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
