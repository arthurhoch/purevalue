package io.github.arthurhoch.purevalue.internet.imsi;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for IMSI.
 * Expects exactly 15 digits.
 */
public final class IMSIValidator implements ValueValidator {
    private static final Pattern PATTERN = Pattern.compile("^\\d{15}$");

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
            throw new IllegalArgumentException("Invalid IMSI: " + raw);
        }
        return cleaned;
    }
}
