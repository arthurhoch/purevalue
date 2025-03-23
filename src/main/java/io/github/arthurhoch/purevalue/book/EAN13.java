package io.github.arthurhoch.purevalue.book;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an EAN-13 barcode.
 */
public record EAN13(String value) implements PureValue<String> {
    private static final EAN13Validator validator = new EAN13Validator();

    public EAN13 {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid EAN-13: " + value);
        }
    }

    @JsonCreator
    public static EAN13 fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static EAN13 of(String value) {
        return new EAN13(value);
    }

    public static EAN13 ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new EAN13(value);
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
