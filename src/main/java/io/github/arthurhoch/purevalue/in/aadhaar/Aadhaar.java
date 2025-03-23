// File: Aadhaar.java
package io.github.arthurhoch.purevalue.in.aadhaar;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Indian Aadhaar.
 */
public record Aadhaar(String value) implements PureValue<String> {
    private static final AadhaarValidator validator = new AadhaarValidator();

    public Aadhaar {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Aadhaar: " + value);
        }
    }

    @JsonCreator
    public static Aadhaar fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static Aadhaar of(String value) {
        return new Aadhaar(value);
    }

    public static Aadhaar ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new Aadhaar(value);
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
