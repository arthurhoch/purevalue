package io.github.arthurhoch.purevalue.crypto.address;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an Ethereum address.
 */
public record CryptoAddressETH(String value) implements PureValue<String> {
    private static final CryptoAddressETHValidator validator = new CryptoAddressETHValidator();

    public CryptoAddressETH {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Ethereum Address: " + value);
        }
    }

    @JsonCreator
    public static CryptoAddressETH fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static CryptoAddressETH of(String value) {
        return new CryptoAddressETH(value);
    }

    public static CryptoAddressETH ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new CryptoAddressETH(value);
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
