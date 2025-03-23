package io.github.arthurhoch.purevalue.br.pispasep;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

public record PISPASEP(String value) implements PureValue<String> {
    private static final PISPASEPValidator validator = new PISPASEPValidator();

    public PISPASEP {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid PIS/PASEP: " + value);
        }
    }

    @JsonCreator
    public static PISPASEP fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static PISPASEP of(String value) {
        return new PISPASEP(value);
    }

    public static PISPASEP ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new PISPASEP(value);
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
