package io.github.arthurhoch.purevalue.internet.imei;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an IMEI.
 */
public record IMEI(String value) implements PureValue<String> {
    private static final IMEIValidator validator = new IMEIValidator();

    public IMEI {
        value = clean(value);
        if(!isValid(value)) {
            throw new IllegalArgumentException("Invalid IMEI: " + value);
        }
    }

    @JsonCreator
    public static IMEI fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static IMEI of(String value) {
        return new IMEI(value);
    }

    public static IMEI ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new IMEI(value);
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
