package io.github.arthurhoch.purevalue.in.upi;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for UPI ID.
 * Expects a format similar to an email address: local-part@bank.
 * This simplified validator does not enforce domain-specific rules.
 */
public final class UPIIDValidator implements ValueValidator {
    private static final Pattern PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+$");

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
            throw new IllegalArgumentException("Invalid UPI ID: " + raw);
        }
        return cleaned;
    }
}
