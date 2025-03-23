package io.github.arthurhoch.purevalue.in.upi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a UPI ID.
 */
public record UPIID(String value) implements PureValue<String> {
    private static final UPIIDValidator validator = new UPIIDValidator();

    public UPIID {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid UPI ID: " + value);
        }
    }

    @JsonCreator
    public static UPIID fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static UPIID of(String value) {
        return new UPIID(value);
    }

    public static UPIID ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new UPIID(value);
    }

    public static boolean isValid(String value) {
        return validator.isValid(clean(value));
    }

    public static String clean(String value) {
        return validator.clean(value);
    }

    @Override
    public String format() {
        return validator.format(value);
    }
}
