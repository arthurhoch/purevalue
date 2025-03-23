// File: NIN.java
package io.github.arthurhoch.purevalue.uk.nin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing UK National Insurance Number (NIN).
 */
public record NIN(String value) implements PureValue<String> {
    private static final NINValidator validator = new NINValidator();

    public NIN {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid NIN: " + value);
        }
    }

    @JsonCreator
    public static NIN fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static NIN of(String value) {
        return new NIN(value);
    }

    public static NIN ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new NIN(value);
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
