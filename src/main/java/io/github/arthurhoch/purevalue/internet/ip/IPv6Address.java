package io.github.arthurhoch.purevalue.internet.ip;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an IPv6 address.
 */
public record IPv6Address(String value) implements PureValue<String> {
    private static final IPv6AddressValidator validator = new IPv6AddressValidator();

    public IPv6Address {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid IPv6 Address: " + value);
        }
    }

    @JsonCreator
    public static IPv6Address fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static IPv6Address of(String value) {
        return new IPv6Address(value);
    }

    public static IPv6Address ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new IPv6Address(value);
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
