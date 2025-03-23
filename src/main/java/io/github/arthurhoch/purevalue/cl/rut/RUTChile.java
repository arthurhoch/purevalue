// File: RUTChile.java
package io.github.arthurhoch.purevalue.cl.rut;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Chilean RUT (Rol Único Tributario).
 */
public record RUTChile(String value) implements PureValue<String> {
    private static final RUTChileValidator validator = new RUTChileValidator();

    public RUTChile {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid RUTChile: " + value);
        }
    }

    @JsonCreator
    public static RUTChile fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static RUTChile of(String value) {
        return new RUTChile(value);
    }

    public static RUTChile ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new RUTChile(value);
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
