package io.github.arthurhoch.purevalue.ma.cin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Morocco CIN (Carte d’Identité Nationale).
 */
public record MoroccoCIN(String value) implements PureValue<String> {
    private static final MoroccoCINValidator validator = new MoroccoCINValidator();

    public MoroccoCIN {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Morocco CIN: " + value);
        }
    }

    @JsonCreator
    public static MoroccoCIN fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static MoroccoCIN of(String value) {
        return new MoroccoCIN(value);
    }

    public static MoroccoCIN ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new MoroccoCIN(value);
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
