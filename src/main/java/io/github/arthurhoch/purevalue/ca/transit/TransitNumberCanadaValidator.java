package io.github.arthurhoch.purevalue.ca.transit;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Canadian Transit Number.
 * Expects exactly 5 digits.
 */
public final class TransitNumberCanadaValidator implements ValueValidator {
    private static final Pattern PATTERN = Pattern.compile("^\\d{5}$");

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
            throw new IllegalArgumentException("Invalid Transit Number Canada: " + raw);
        }
        return cleaned;
    }
}
