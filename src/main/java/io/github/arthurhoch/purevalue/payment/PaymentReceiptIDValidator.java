package io.github.arthurhoch.purevalue.payment;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Payment Receipt ID.
 * Accepts an alphanumeric string (with optional dashes or underscores)
 * with length between 8 and 64 characters.
 */
public final class PaymentReceiptIDValidator implements ValueValidator {
    // Updated: Use uppercase conversion.
    private static final Pattern PATTERN = Pattern.compile("^[A-Z0-9_-]{8,64}$");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return PATTERN.matcher(cleaned).matches();
    }

    public String clean(String value) {
        return (value == null) ? null : value.trim().toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Payment Receipt ID: " + raw);
        }
        return cleaned;
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }
}
