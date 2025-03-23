package io.github.arthurhoch.purevalue.book;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for ISRC (International Standard Recording Code).
 * Expects 12 characters, typically in the form "CCXXXYYNNNNN" where:
 * - CC: two-letter country code,
 * - XXX: three-character registrant code (alphanumeric),
 * - YY: two-digit year,
 * - NNNNN: five-digit designation code.
 * Accepts input with or without hyphens.
 */
public final class ISRCValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("^[A-Z]{2}[A-Z0-9]{3}\\d{7}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return RAW_PATTERN.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        // Accept formatted version: "CC-XXX-YY-NNNNN"
        return value != null && (value.trim().matches("^[A-Z]{2}-[A-Z0-9]{3}-\\d{2}-\\d{5}$") || isValid(value));
    }

    public String clean(String value) {
        return (value == null) ? null : value.replaceAll("[-\\s]", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 12 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid ISRC: " + raw);
        }
        // Format as "CC-XXX-YY-NNNNN"
        return cleaned.substring(0,2) + "-" + cleaned.substring(2,5) + "-" + cleaned.substring(5,7) + "-" + cleaned.substring(7);
    }
}
