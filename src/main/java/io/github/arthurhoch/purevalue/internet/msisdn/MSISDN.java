package io.github.arthurhoch.purevalue.internet.msisdn;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an MSISDN (mobile phone number in E.164 format).
 */
public record MSISDN(String value) implements PureValue<String> {
    private static final MSISDNValidator validator = new MSISDNValidator();

    public MSISDN {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid MSISDN: " + value);
        }
    }

    @JsonCreator
    public static MSISDN fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static MSISDN of(String value) {
        return new MSISDN(value);
    }

    public static MSISDN ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new MSISDN(value);
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
