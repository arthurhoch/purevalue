package io.github.arthurhoch.purevalue.crypto.address;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Bitcoin addresses.
 * Accepts:
 * - Legacy/Base58 addresses: starting with '1' or '3', 26-35 characters,
 * - Bech32 addresses: starting with 'bc1' (case-insensitive), 26-90 characters.
 */
public final class CryptoAddressBTCValidator implements ValueValidator {

    // Regex for legacy (Base58Check) addresses
    private static final Pattern LEGACY_PATTERN = Pattern.compile("^[13][a-km-zA-HJ-NP-Z1-9]{25,34}$");
    // Regex for Bech32 addresses (Bitcoin mainnet)
    private static final Pattern BECH32_PATTERN = Pattern.compile("^(bc1)[0-9a-z]{25,39}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return LEGACY_PATTERN.matcher(cleaned).matches() || BECH32_PATTERN.matcher(cleaned).matches();
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
            throw new IllegalArgumentException("Invalid Bitcoin Address: " + raw);
        }
        return cleaned;
    }
}
