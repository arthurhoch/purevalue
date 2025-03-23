package io.github.arthurhoch.purevalue.trade.hscode;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an HS Code.
 */
public record HSCode(String value) implements PureValue<String> {
    private static final HSCodeValidator validator = new HSCodeValidator();

    public HSCode {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid HS Code: " + value);
        }
    }

    @JsonCreator
    public static HSCode fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static HSCode of(String value) {
        return new HSCode(value);
    }

    public static HSCode ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new HSCode(value);
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
