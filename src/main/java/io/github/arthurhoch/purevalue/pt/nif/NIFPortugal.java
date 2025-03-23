// File: NIFPortugal.java
package io.github.arthurhoch.purevalue.pt.nif;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Portuguese NIF (Número de Identificação Fiscal).
 */
public record NIFPortugal(String value) implements PureValue<String> {
    private static final NIFPortugalValidator validator = new NIFPortugalValidator();

    public NIFPortugal {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid NIFPortugal: " + value);
        }
    }

    @JsonCreator
    public static NIFPortugal fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static NIFPortugal of(String value) {
        return new NIFPortugal(value);
    }

    public static NIFPortugal ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new NIFPortugal(value);
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
