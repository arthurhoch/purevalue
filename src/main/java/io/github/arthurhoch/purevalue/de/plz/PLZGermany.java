package io.github.arthurhoch.purevalue.de.plz;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a German postal code (PLZ).
 */
public record PLZGermany(String value) implements PureValue<String> {
    private static final PLZGermanyValidator validator = new PLZGermanyValidator();

    public PLZGermany {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid PLZ Germany: " + value);
        }
    }

    @JsonCreator
    public static PLZGermany fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static PLZGermany of(String value) {
        return new PLZGermany(value);
    }

    public static PLZGermany ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new PLZGermany(value);
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
