// File: Foedselsnummer.java
package io.github.arthurhoch.purevalue.no.fnr;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Norwegian Foedselsnummer.
 */
public record Foedselsnummer(String value) implements PureValue<String> {
    private static final FoedselsnummerValidator validator = new FoedselsnummerValidator();

    public Foedselsnummer {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Foedselsnummer: " + value);
        }
    }

    @JsonCreator
    public static Foedselsnummer fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static Foedselsnummer of(String value) {
        return new Foedselsnummer(value);
    }

    public static Foedselsnummer ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new Foedselsnummer(value);
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
