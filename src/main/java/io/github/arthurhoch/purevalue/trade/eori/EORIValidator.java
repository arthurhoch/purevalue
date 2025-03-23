package io.github.arthurhoch.purevalue.trade.eori;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for EORI.
 * Expects a string starting with two letters followed by 8 to 15 digits.
 */
public final class EORIValidator implements ValueValidator {
    private static final Pattern PATTERN = Pattern.compile("^[A-Z]{2}\\d{8,15}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        return PATTERN.matcher(value.trim()).matches();
    }

    public String clean(String value) {
        return (value == null) ? null : value.trim().toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid EORI: " + raw);
        }
        return cleaned;
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }
}
