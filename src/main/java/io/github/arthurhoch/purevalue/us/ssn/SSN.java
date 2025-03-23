// File: SSN.java
package io.github.arthurhoch.purevalue.us.ssn;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a US Social Security Number.
 */
public record SSN(String value) implements PureValue<String> {
    private static final SSNValidator validator = new SSNValidator();

    public SSN {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid SSN: " + value);
        }
    }

    @JsonCreator
    public static SSN fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static SSN of(String value) {
        return new SSN(value);
    }

    public static SSN ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new SSN(value);
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
