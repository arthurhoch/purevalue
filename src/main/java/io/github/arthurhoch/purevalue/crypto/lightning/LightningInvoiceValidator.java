package io.github.arthurhoch.purevalue.crypto.lightning;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for a Lightning Invoice (BOLT-11).
 * This simplified validator accepts invoice strings that start with one of:
 * "lnbc" (Bitcoin mainnet), "lntb" (Bitcoin testnet), or "lnbcrt" (Bitcoin regtest),
 * followed by one or more alphanumeric characters.
 */
public final class LightningInvoiceValidator implements ValueValidator {

    // Simplified regex; real invoices are more complex.
    private static final Pattern PATTERN = Pattern.compile("^(lnbc|lntb|lnbcrt)[0-9a-z]+$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return PATTERN.matcher(cleaned).matches();
    }

    public String clean(String value) {
        return (value == null) ? null : value.trim().toLowerCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Lightning Invoice: " + raw);
        }
        return cleaned;
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }
}
