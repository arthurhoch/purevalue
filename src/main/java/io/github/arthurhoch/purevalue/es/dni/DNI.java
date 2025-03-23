// File: DNI.java
package io.github.arthurhoch.purevalue.es.dni;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Spanish DNI.
 */
public record DNI(String value) implements PureValue<String> {
    private static final DNIValidator validator = new DNIValidator();

    public DNI {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid DNI: " + value);
        }
    }

    @JsonCreator
    public static DNI fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static DNI of(String value) {
        return new DNI(value);
    }

    public static DNI ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new DNI(value);
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
