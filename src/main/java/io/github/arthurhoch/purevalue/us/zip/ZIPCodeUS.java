package io.github.arthurhoch.purevalue.us.zip;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a US ZIP Code.
 */
public record ZIPCodeUS(String value) implements PureValue<String> {
    private static final ZIPCodeUSValidator validator = new ZIPCodeUSValidator();

    public ZIPCodeUS {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid US ZIP Code: " + value);
        }
    }

    @JsonCreator
    public static ZIPCodeUS fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static ZIPCodeUS of(String value) {
        return new ZIPCodeUS(value);
    }

    public static ZIPCodeUS ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new ZIPCodeUS(value);
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
