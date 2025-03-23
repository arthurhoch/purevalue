package io.github.arthurhoch.purevalue.br.cnae;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

public record CNAE(String value) implements PureValue<String> {
    private static final CNAEValidator validator = new CNAEValidator();

    public CNAE {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid CNAE: " + value);
        }
    }

    @JsonCreator
    public static CNAE fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static CNAE of(String value) {
        return new CNAE(value);
    }

    public static CNAE ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new CNAE(value);
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
