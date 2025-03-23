// File: DNIArgentina.java
package io.github.arthurhoch.purevalue.ar.dni;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Argentine DNI.
 */
public record DNIArgentina(String value) implements PureValue<String> {
    private static final DNIArgentinaValidator validator = new DNIArgentinaValidator();

    public DNIArgentina {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid DNI: " + value);
        }
    }

    @JsonCreator
    public static DNIArgentina fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static DNIArgentina of(String value) {
        return new DNIArgentina(value);
    }

    public static DNIArgentina ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new DNIArgentina(value);
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
