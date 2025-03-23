package io.github.arthurhoch.purevalue.trade.bl;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for a generic Bill of Lading Number.
 * Accepts an alphanumeric string (possibly containing dashes) between 8 and 20 characters.
 */
public final class BillOfLadingNumberValidator implements ValueValidator {
    // Allow letters, digits, and dashes. Length after cleaning (removing spaces) must be between 8 and 20.
    private static final Pattern PATTERN = Pattern.compile("^[A-Z0-9-]{8,20}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return PATTERN.matcher(cleaned).matches();
    }

    public String clean(String value) {
        return (value == null) ? null : value.trim().toUpperCase().replaceAll("\\s+", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() < 8 || cleaned.length() > 20 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Bill of Lading Number: " + raw);
        }
        return cleaned;
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }
}
