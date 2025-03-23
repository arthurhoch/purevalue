package io.github.arthurhoch.purevalue.fr.siret;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing French SIRET.
 */
public record SIRET(String value) implements PureValue<String> {
    private static final SIRETValidator validator = new SIRETValidator();

    public SIRET {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid SIRET: " + value);
        }
    }

    @JsonCreator
    public static SIRET fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static SIRET of(String value) {
        return new SIRET(value);
    }

    public static SIRET ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new SIRET(value);
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
