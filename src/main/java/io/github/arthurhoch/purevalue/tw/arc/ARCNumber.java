// File: ARCNumber.java
package io.github.arthurhoch.purevalue.tw.arc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Taiwan ARC Number (Alien Resident Certificate).
 */
public record ARCNumber(String value) implements PureValue<String> {
    private static final ARCNumberValidator validator = new ARCNumberValidator();

    public ARCNumber {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid ARC Number: " + value);
        }
    }

    @JsonCreator
    public static ARCNumber fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static ARCNumber of(String value) {
        return new ARCNumber(value);
    }

    public static ARCNumber ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new ARCNumber(value);
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
