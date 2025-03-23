package io.github.arthurhoch.purevalue.book;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a GTIN (EAN/UPC).
 */
public record GTIN(String value) implements PureValue<String> {
    private static final GTINValidator validator = new GTINValidator();

    public GTIN {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid GTIN: " + value);
        }
    }

    @JsonCreator
    public static GTIN fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static GTIN of(String value) {
        return new GTIN(value);
    }

    public static GTIN ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new GTIN(value);
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
