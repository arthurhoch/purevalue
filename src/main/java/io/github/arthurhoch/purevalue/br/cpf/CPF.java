package io.github.arthurhoch.purevalue.br.cpf;

import io.github.arthurhoch.purevalue.core.PureValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public record CPF(String value) implements PureValue<String> {

    private static final CPFValidator validator = new CPFValidator();

    public CPF {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid CPF: " + value);
        }
    }

    @JsonCreator
    public static CPF fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static CPF of(String value) {
        return new CPF(value);
    }

    public static CPF ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new CPF(value);
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
