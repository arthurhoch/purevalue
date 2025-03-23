package io.github.arthurhoch.purevalue.in.pincode;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an Indian PIN Code.
 */
public record PinCodeIndia(String value) implements PureValue<String> {
    private static final PinCodeIndiaValidator validator = new PinCodeIndiaValidator();

    public PinCodeIndia {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid PinCodeIndia: " + value);
        }
    }

    @JsonCreator
    public static PinCodeIndia fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static PinCodeIndia of(String value) {
        return new PinCodeIndia(value);
    }

    public static PinCodeIndia ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new PinCodeIndia(value);
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
