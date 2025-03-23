// File: SouthAfricaIDNumber.java
package io.github.arthurhoch.purevalue.za.id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing South African ID Number.
 */
public record SouthAfricaIDNumber(String value) implements PureValue<String> {
    private static final SouthAfricaIDNumberValidator validator = new SouthAfricaIDNumberValidator();

    public SouthAfricaIDNumber {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid South Africa ID Number: " + value);
        }
    }

    @JsonCreator
    public static SouthAfricaIDNumber fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static SouthAfricaIDNumber of(String value) {
        return new SouthAfricaIDNumber(value);
    }

    public static SouthAfricaIDNumber ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new SouthAfricaIDNumber(value);
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
