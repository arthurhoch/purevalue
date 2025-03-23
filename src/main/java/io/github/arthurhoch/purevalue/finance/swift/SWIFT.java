package io.github.arthurhoch.purevalue.finance.swift;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a SWIFT/BIC code.
 */
public record SWIFT(String value) implements PureValue<String> {
    private static final SWIFTValidator validator = new SWIFTValidator();

    public SWIFT {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid SWIFT code: " + value);
        }
    }

    @JsonCreator
    public static SWIFT fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static SWIFT of(String value) {
        return new SWIFT(value);
    }

    public static SWIFT ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new SWIFT(value);
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
