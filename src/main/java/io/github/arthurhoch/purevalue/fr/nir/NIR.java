// File: NIR.java
package io.github.arthurhoch.purevalue.fr.nir;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing French NIR (Numéro de Sécurité Sociale).
 */
public record NIR(String value) implements PureValue<String> {
    private static final NIRValidator validator = new NIRValidator();

    public NIR {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid NIR: " + value);
        }
    }

    @JsonCreator
    public static NIR fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static NIR of(String value) {
        return new NIR(value);
    }

    public static NIR ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new NIR(value);
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
