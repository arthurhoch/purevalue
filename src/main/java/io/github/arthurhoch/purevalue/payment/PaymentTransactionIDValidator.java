package io.github.arthurhoch.purevalue.payment;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Payment Transaction ID.
 * Accepts an alphanumeric string that may include dashes and underscores.
 * Length must be between 8 and 64 characters.
 */
public final class PaymentTransactionIDValidator implements ValueValidator {
    // Updated: Convert input to uppercase before matching.
    private static final Pattern PATTERN = Pattern.compile("^[A-Z0-9_-]{8,64}$");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return PATTERN.matcher(cleaned).matches();
    }

    public String clean(String value) {
        // Trim and convert to uppercase.
        return (value == null) ? null : value.trim().toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Payment Transaction ID: " + raw);
        }
        return cleaned;
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }
}
