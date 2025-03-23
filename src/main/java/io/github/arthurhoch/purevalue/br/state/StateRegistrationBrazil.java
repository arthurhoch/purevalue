package io.github.arthurhoch.purevalue.br.state;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

public record StateRegistrationBrazil(String value) implements PureValue<String> {
    private static final StateRegistrationBrazilValidator validator = new StateRegistrationBrazilValidator();

    public StateRegistrationBrazil {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid State Registration: " + value);
        }
    }

    @JsonCreator
    public static StateRegistrationBrazil fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static StateRegistrationBrazil of(String value) {
        return new StateRegistrationBrazil(value);
    }

    public static StateRegistrationBrazil ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new StateRegistrationBrazil(value);
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
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
