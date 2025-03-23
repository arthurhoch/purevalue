package io.github.arthurhoch.purevalue.crypto.address;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Bitcoin address.
 */
public record CryptoAddressBTC(String value) implements PureValue<String> {
    private static final CryptoAddressBTCValidator validator = new CryptoAddressBTCValidator();

    public CryptoAddressBTC {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Bitcoin Address: " + value);
        }
    }

    @JsonCreator
    public static CryptoAddressBTC fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static CryptoAddressBTC of(String value) {
        return new CryptoAddressBTC(value);
    }

    public static CryptoAddressBTC ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new CryptoAddressBTC(value);
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
