// File: NumeroSegurancaSocialPT.java
package io.github.arthurhoch.purevalue.pt.niss;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Portuguese Social Security Number (NISS).
 */
public record NumeroSegurancaSocialPT(String value) implements PureValue<String> {
    private static final NumeroSegurancaSocialPTValidator validator = new NumeroSegurancaSocialPTValidator();

    public NumeroSegurancaSocialPT {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid NumeroSegurancaSocialPT: " + value);
        }
    }

    @JsonCreator
    public static NumeroSegurancaSocialPT fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static NumeroSegurancaSocialPT of(String value) {
        return new NumeroSegurancaSocialPT(value);
    }

    public static NumeroSegurancaSocialPT ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new NumeroSegurancaSocialPT(value);
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

    @Override
    public String toString() {
        return value;
    }
}
