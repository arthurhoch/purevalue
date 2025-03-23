package io.github.arthurhoch.purevalue.br.titulo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

public record TituloEleitor(String value) implements PureValue<String> {
    private static final TituloEleitorValidator validator = new TituloEleitorValidator();

    public TituloEleitor {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Título de Eleitor: " + value);
        }
    }

    @JsonCreator
    public static TituloEleitor fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static TituloEleitor of(String value) {
        return new TituloEleitor(value);
    }

    public static TituloEleitor ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new TituloEleitor(value);
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
