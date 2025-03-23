package io.github.arthurhoch.purevalue.internet.asn;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Autonomous System Number (ASN).
 * Accepts a numeric string representing a 32-bit unsigned integer (0 to 4294967295).
 */
public final class ASNValidator implements ValueValidator {
    private static final Pattern DIGITS_PATTERN = Pattern.compile("^\\d+$");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String trimmed = value.trim();
        if (!DIGITS_PATTERN.matcher(trimmed).matches()) return false;
        try {
            long asn = Long.parseLong(trimmed);
            return asn >= 0 && asn <= 4294967295L;
        } catch (NumberFormatException e) {
            return false;
        }
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
            throw new IllegalArgumentException("Invalid ASN: " + raw);
        }
        return cleaned;
    }
}
