package io.github.arthurhoch.purevalue.finance.swift;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for SWIFT/BIC code.
 * Accepts either 8 or 11 characters:
 * Format: 4 letters (bank code), 2 letters (country code), 2 alphanumeric (location code),
 * optional 3 alphanumeric (branch code).
 */
public final class SWIFTValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("^[A-Z]{4}[A-Z]{2}[A-Z0-9]{2}([A-Z0-9]{3})?$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return RAW_PATTERN.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }

    public String clean(String value) {
        return (value == null) ? null : value.trim().toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid SWIFT code: " + raw);
        }
        return cleaned;
    }
}
