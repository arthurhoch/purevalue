package io.github.arthurhoch.purevalue.postbox;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for a generic PO Box Number.
 * Accepts 1 to 30 characters consisting of letters, digits, spaces, and hyphens.
 */
public final class POBoxNumberValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("^[A-Z0-9\\s\\-]{1,30}$", Pattern.CASE_INSENSITIVE);

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
        return (value == null) ? null : value.trim();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.isEmpty() || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid PO Box Number: " + raw);
        }
        return cleaned;
    }
}
