package io.github.arthurhoch.purevalue.internet.iccid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an ICCID.
 */
public record ICCID(String value) implements PureValue<String> {
    private static final ICCIDValidator validator = new ICCIDValidator();

    public ICCID {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid ICCID: " + value);
        }
    }

    @JsonCreator
    public static ICCID fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static ICCID of(String value) {
        return new ICCID(value);
    }

    public static ICCID ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new ICCID(value);
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
