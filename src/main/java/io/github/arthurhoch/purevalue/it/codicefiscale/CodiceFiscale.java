// File: CodiceFiscale.java
package io.github.arthurhoch.purevalue.it.codicefiscale;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Italian Codice Fiscale.
 */
public record CodiceFiscale(String value) implements PureValue<String> {
    private static final CodiceFiscaleValidator validator = new CodiceFiscaleValidator();

    public CodiceFiscale {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Codice Fiscale: " + value);
        }
    }

    @JsonCreator
    public static CodiceFiscale fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static CodiceFiscale of(String value) {
        return new CodiceFiscale(value);
    }

    public static CodiceFiscale ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new CodiceFiscale(value);
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
