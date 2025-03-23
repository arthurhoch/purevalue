package io.github.arthurhoch.purevalue.ch.postal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Swiss Postal Code.
 */
public record PostalCodeSwitzerland(String value) implements PureValue<String> {
    private static final PostalCodeSwitzerlandValidator validator = new PostalCodeSwitzerlandValidator();

    public PostalCodeSwitzerland {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Postal Code Switzerland: " + value);
        }
    }

    @JsonCreator
    public static PostalCodeSwitzerland fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static PostalCodeSwitzerland of(String value) {
        return new PostalCodeSwitzerland(value);
    }

    public static PostalCodeSwitzerland ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new PostalCodeSwitzerland(value);
    }

    public static boolean isValid(String value) {
        return validator.isValid(value);
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
