// File: EIN.java
package io.github.arthurhoch.purevalue.us.ein;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a US Employer Identification Number (EIN).
 */
public record EIN(String value) implements PureValue<String> {
    private static final EINValidator validator = new EINValidator();

    public EIN {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid EIN: " + value);
        }
    }

    @JsonCreator
    public static EIN fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static EIN of(String value) {
        return new EIN(value);
    }

    public static EIN ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new EIN(value);
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
