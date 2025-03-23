package io.github.arthurhoch.purevalue.internet.ip;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an IPv4 address.
 */
public record IPv4Address(String value) implements PureValue<String> {
    private static final IPv4AddressValidator validator = new IPv4AddressValidator();

    public IPv4Address {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid IPv4 Address: " + value);
        }
    }

    @JsonCreator
    public static IPv4Address fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static IPv4Address of(String value) {
        return new IPv4Address(value);
    }

    public static IPv4Address ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new IPv4Address(value);
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
