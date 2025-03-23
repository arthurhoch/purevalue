package io.github.arthurhoch.purevalue.br.boleto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Brazilian Boleto.
 */
public record BoletoBrazil(String value) implements PureValue<String> {
    private static final BoletoBrazilValidator validator = new BoletoBrazilValidator();

    public BoletoBrazil {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Boleto Brazil: " + value);
        }
    }

    @JsonCreator
    public static BoletoBrazil fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static BoletoBrazil of(String value) {
        return new BoletoBrazil(value);
    }

    public static BoletoBrazil ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new BoletoBrazil(value);
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
