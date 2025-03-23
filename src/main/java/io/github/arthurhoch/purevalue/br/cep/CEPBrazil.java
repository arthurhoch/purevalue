package io.github.arthurhoch.purevalue.br.cep;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

public record CEPBrazil(String value) implements PureValue<String> {
    private static final CEPBrazilValidator validator = new CEPBrazilValidator();

    public CEPBrazil {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid CEP: " + value);
        }
    }

    @JsonCreator
    public static CEPBrazil fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static CEPBrazil of(String value) {
        return new CEPBrazil(value);
    }

    public static CEPBrazil ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new CEPBrazil(value);
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
