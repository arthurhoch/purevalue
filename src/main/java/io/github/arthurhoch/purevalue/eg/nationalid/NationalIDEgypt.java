// File: NationalIDEgypt.java
package io.github.arthurhoch.purevalue.eg.nationalid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Egyptian National ID.
 */
public record NationalIDEgypt(String value) implements PureValue<String> {
    private static final NationalIDEgyptValidator validator = new NationalIDEgyptValidator();

    public NationalIDEgypt {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid National IDEgypt: " + value);
        }
    }

    @JsonCreator
    public static NationalIDEgypt fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static NationalIDEgypt of(String value) {
        return new NationalIDEgypt(value);
    }

    public static NationalIDEgypt ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new NationalIDEgypt(value);
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
