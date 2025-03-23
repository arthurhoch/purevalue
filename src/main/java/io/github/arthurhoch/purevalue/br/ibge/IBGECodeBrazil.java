package io.github.arthurhoch.purevalue.br.ibge;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an IBGE Code for Brazilian municipalities.
 */
public record IBGECodeBrazil(String value) implements PureValue<String> {
    private static final IBGECodeBrazilValidator validator = new IBGECodeBrazilValidator();

    public IBGECodeBrazil {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid IBGE Code Brazil: " + value);
        }
    }

    @JsonCreator
    public static IBGECodeBrazil fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static IBGECodeBrazil of(String value) {
        return new IBGECodeBrazil(value);
    }

    public static IBGECodeBrazil ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new IBGECodeBrazil(value);
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
