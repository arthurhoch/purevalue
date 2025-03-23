package io.github.arthurhoch.purevalue.be.vat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Belgian VAT Number.
 */
public record VATNumberBelgium(String value) implements PureValue<String> {
    private static final VATNumberBelgiumValidator validator = new VATNumberBelgiumValidator();

    public VATNumberBelgium {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid VAT Number Belgium: " + value);
        }
    }

    @JsonCreator
    public static VATNumberBelgium fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static VATNumberBelgium of(String value) {
        return new VATNumberBelgium(value);
    }

    public static VATNumberBelgium ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new VATNumberBelgium(value);
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
