package io.github.arthurhoch.purevalue.internet.mac;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a MAC Address.
 */
public record MACAddress(String value) implements PureValue<String> {
    private static final MACAddressValidator validator = new MACAddressValidator();

    public MACAddress {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid MAC Address: " + value);
        }
    }

    @JsonCreator
    public static MACAddress fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static MACAddress of(String value) {
        return new MACAddress(value);
    }

    public static MACAddress ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new MACAddress(value);
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
