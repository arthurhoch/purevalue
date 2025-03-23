package io.github.arthurhoch.purevalue.br.cnpj;

import io.github.arthurhoch.purevalue.core.PureValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public record CNPJ(String value) implements PureValue<String> {

    private static final CNPJValidator validator = new CNPJValidator();

    public CNPJ {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid CNPJ: " + value);
        }
    }

    @JsonCreator
    public static CNPJ fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static CNPJ of(String value) {
        return new CNPJ(value);
    }

    public static CNPJ ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new CNPJ(value);
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
