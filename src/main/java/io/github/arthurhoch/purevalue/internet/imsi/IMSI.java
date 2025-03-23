package io.github.arthurhoch.purevalue.internet.imsi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an IMSI.
 */
public record IMSI(String value) implements PureValue<String> {
    private static final IMSIValidator validator = new IMSIValidator();

    public IMSI {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid IMSI: " + value);
        }
    }

    @JsonCreator
    public static IMSI fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static IMSI of(String value) {
        return new IMSI(value);
    }

    public static IMSI ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new IMSI(value);
    }

    public static boolean isValid(String value) {
        return validator.isValid(value);
    }

    public static String clean(String value) {
        return validator.clean(value);
    }

    @Override
    public String format() {
        return validator.format(value);
    }
}
