package io.github.arthurhoch.purevalue.trade.hscode;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for HS Code.
 * Accepts 6, 8, or 10 digits.
 */
public final class HSCodeValidator implements ValueValidator {
    private static final Pattern PATTERN = Pattern.compile("^(\\d{6}|\\d{8}|\\d{10})$");

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
            throw new IllegalArgumentException("Invalid HS Code: " + raw);
        }
        return cleaned;
    }
}
