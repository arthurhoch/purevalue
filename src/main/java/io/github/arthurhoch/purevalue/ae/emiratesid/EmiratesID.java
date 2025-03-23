// File: EmiratesID.java
package io.github.arthurhoch.purevalue.ae.emiratesid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing UAE Emirates ID.
 */
public record EmiratesID(String value) implements PureValue<String> {
    private static final EmiratesIDValidator validator = new EmiratesIDValidator();

    public EmiratesID {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Emirates ID: " + value);
        }
    }

    @JsonCreator
    public static EmiratesID fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static EmiratesID of(String value) {
        return new EmiratesID(value);
    }

    public static EmiratesID ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new EmiratesID(value);
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
