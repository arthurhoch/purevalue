// File: USPassportCardNumber.java
package io.github.arthurhoch.purevalue.us.passport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a US Passport Card Number.
 */
public record USPassportCardNumber(String value) implements PureValue<String> {
    private static final USPassportCardNumberValidator validator = new USPassportCardNumberValidator();

    public USPassportCardNumber {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid US Passport Card Number: " + value);
        }
    }

    @JsonCreator
    public static USPassportCardNumber fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static USPassportCardNumber of(String value) {
        return new USPassportCardNumber(value);
    }

    public static USPassportCardNumber ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new USPassportCardNumber(value);
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

    @Override
    public String toString() {
        return value;
    }
}
