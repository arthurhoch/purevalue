package io.github.arthurhoch.purevalue.br.cfop;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Brazilian CFOP.
 */
public record CFOPBrazil(String value) implements PureValue<String> {
    private static final CFOPBrazilValidator validator = new CFOPBrazilValidator();

    public CFOPBrazil {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid CFOP Brazil: " + value);
        }
    }

    @JsonCreator
    public static CFOPBrazil fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static CFOPBrazil of(String value) {
        return new CFOPBrazil(value);
    }

    public static CFOPBrazil ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new CFOPBrazil(value);
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
