package io.github.arthurhoch.purevalue.crypto.address;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Ethereum addresses.
 * Expects exactly 42 characters: a "0x" prefix followed by 40 hexadecimal characters.
 */
public final class CryptoAddressETHValidator implements ValueValidator {
    private static final Pattern PATTERN = Pattern.compile("^0x[a-fA-F0-9]{40}$");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        return PATTERN.matcher(value.trim()).matches();
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }

    public String clean(String value) {
        return (value == null) ? null : value.trim();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Ethereum Address: " + raw);
        }
        return cleaned;
    }
}
