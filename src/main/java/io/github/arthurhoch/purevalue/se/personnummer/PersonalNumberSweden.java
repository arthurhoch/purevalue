// File: PersonalNumberSweden.java
package io.github.arthurhoch.purevalue.se.personnummer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Swedish Personal Number (Personnummer).
 */
public record PersonalNumberSweden(String value) implements PureValue<String> {
    private static final PersonalNumberSwedenValidator validator = new PersonalNumberSwedenValidator();

    public PersonalNumberSweden {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Personal Number Sweden: " + value);
        }
    }

    @JsonCreator
    public static PersonalNumberSweden fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static PersonalNumberSweden of(String value) {
        return new PersonalNumberSweden(value);
    }

    public static PersonalNumberSweden ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new PersonalNumberSweden(value);
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
