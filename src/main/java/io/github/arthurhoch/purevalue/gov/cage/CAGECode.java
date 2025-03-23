package io.github.arthurhoch.purevalue.gov.cage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a CAGE Code.
 */
public record CAGECode(String value) implements PureValue<String> {
    private static final CAGECodeValidator validator = new CAGECodeValidator();

    public CAGECode {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid CAGE Code: " + value);
        }
    }

    @JsonCreator
    public static CAGECode fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static CAGECode of(String value) {
        return new CAGECode(value);
    }

    public static CAGECode ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new CAGECode(value);
    }

    public static boolean isValid(String value) {
        return validator.isValid(value);
    }

    public static String clean(String value) {
        return validator.clean(value);
    }

    @Override
    public String format() {
        return validator.format(value);
    }
}
