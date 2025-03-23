package io.github.arthurhoch.purevalue.pt.postal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Portuguese Postal Code (Código Postal).
 */
public record CodigoPostalPortugal(String value) implements PureValue<String> {
    private static final CodigoPostalPortugalValidator validator = new CodigoPostalPortugalValidator();

    public CodigoPostalPortugal {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Codigo Postal Portugal: " + value);
        }
    }

    @JsonCreator
    public static CodigoPostalPortugal fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static CodigoPostalPortugal of(String value) {
        return new CodigoPostalPortugal(value);
    }

    public static CodigoPostalPortugal ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new CodigoPostalPortugal(value);
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
}
