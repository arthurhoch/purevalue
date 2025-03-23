package io.github.arthurhoch.purevalue.ca.postal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Canadian Postal Code.
 */
public record PostalCodeCanada(String value) implements PureValue<String> {
    private static final PostalCodeCanadaValidator validator = new PostalCodeCanadaValidator();

    public PostalCodeCanada {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Postal Code Canada: " + value);
        }
    }

    @JsonCreator
    public static PostalCodeCanada fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static PostalCodeCanada of(String value) {
        return new PostalCodeCanada(value);
    }

    public static PostalCodeCanada ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new PostalCodeCanada(value);
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
