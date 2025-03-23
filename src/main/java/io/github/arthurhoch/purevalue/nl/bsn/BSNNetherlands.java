// File: BSNNetherlands.java
package io.github.arthurhoch.purevalue.nl.bsn;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Dutch BSN.
 */
public record BSNNetherlands(String value) implements PureValue<String> {
    private static final BSNNetherlandsValidator validator = new BSNNetherlandsValidator();

    public BSNNetherlands {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid BSN: " + value);
        }
    }

    @JsonCreator
    public static BSNNetherlands fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static BSNNetherlands of(String value) {
        return new BSNNetherlands(value);
    }

    public static BSNNetherlands ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new BSNNetherlands(value);
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
