// File: NIE.java
package io.github.arthurhoch.purevalue.es.nie;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Spanish NIE.
 */
public record NIE(String value) implements PureValue<String> {
    private static final NIEValidator validator = new NIEValidator();

    public NIE {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid NIE: " + value);
        }
    }

    @JsonCreator
    public static NIE fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static NIE of(String value) {
        return new NIE(value);
    }

    public static NIE ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new NIE(value);
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
