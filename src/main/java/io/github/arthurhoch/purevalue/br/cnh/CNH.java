package io.github.arthurhoch.purevalue.br.cnh;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

public record CNH(String value) implements PureValue<String> {
    private static final CNHValidator validator = new CNHValidator();

    public CNH {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid CNH: " + value);
        }
    }

    @JsonCreator
    public static CNH fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static CNH of(String value) {
        return new CNH(value);
    }

    public static CNH ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new CNH(value);
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
