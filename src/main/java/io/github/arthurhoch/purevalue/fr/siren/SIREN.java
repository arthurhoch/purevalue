package io.github.arthurhoch.purevalue.fr.siren;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing French SIREN.
 */
public record SIREN(String value) implements PureValue<String> {
    private static final SIRENValidator validator = new SIRENValidator();

    public SIREN {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid SIREN: " + value);
        }
    }

    @JsonCreator
    public static SIREN fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static SIREN of(String value) {
        return new SIREN(value);
    }

    public static SIREN ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new SIREN(value);
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
