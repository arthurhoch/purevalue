package io.github.arthurhoch.purevalue.ie.eircode;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an Irish Eircode.
 */
public record EircodeIreland(String value) implements PureValue<String> {
    private static final EircodeIrelandValidator validator = new EircodeIrelandValidator();

    public EircodeIreland {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Eircode Ireland: " + value);
        }
    }

    @JsonCreator
    public static EircodeIreland fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static EircodeIreland of(String value) {
        return new EircodeIreland(value);
    }

    public static EircodeIreland ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new EircodeIreland(value);
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
