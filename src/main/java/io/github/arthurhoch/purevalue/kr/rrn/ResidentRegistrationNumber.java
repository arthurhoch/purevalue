// File: ResidentRegistrationNumber.java
package io.github.arthurhoch.purevalue.kr.rrn;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing South Korean Resident Registration Number (RRN).
 */
public record ResidentRegistrationNumber(String value) implements PureValue<String> {
    private static final ResidentRegistrationNumberValidator validator = new ResidentRegistrationNumberValidator();

    public ResidentRegistrationNumber {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Resident Registration Number: " + value);
        }
    }

    @JsonCreator
    public static ResidentRegistrationNumber fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static ResidentRegistrationNumber of(String value) {
        return new ResidentRegistrationNumber(value);
    }

    public static ResidentRegistrationNumber ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new ResidentRegistrationNumber(value);
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
