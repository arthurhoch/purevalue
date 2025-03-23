package io.github.arthurhoch.purevalue.de.hrb;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for German HRB (Handelsregister) number.
 * Expects the number to start with "HRB" (case-insensitive), optionally followed by a space, then 1 to 10 digits.
 */
public final class HRBGermanyValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("(?i)^HRB[ ]?\\d{1,10}$");

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
        return value == null ? null : value.replaceAll("\\s", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid HRB Germany: " + raw);
        }
        return cleaned;
    }
}
