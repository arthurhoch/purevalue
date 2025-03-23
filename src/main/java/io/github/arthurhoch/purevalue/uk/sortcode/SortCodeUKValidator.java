package io.github.arthurhoch.purevalue.uk.sortcode;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for UK Sort Code.
 * Expects exactly 6 digits.
 */
public final class SortCodeUKValidator implements ValueValidator {
    private static final Pattern PATTERN = Pattern.compile("^\\d{6}$");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        return PATTERN.matcher(value.trim()).matches();
    }

    public boolean isFormatted(String value) {
        // Accepts either 6 contiguous digits or "XX-XX-XX"
        return value != null && (value.trim().matches("^\\d{6}$") || value.trim().matches("^\\d{2}-\\d{2}-\\d{2}$"));
    }

    public String clean(String value) {
        return (value == null) ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Sort Code UK: " + raw);
        }
        // Format as "XX-XX-XX"
        return cleaned.substring(0, 2) + "-" + cleaned.substring(2, 4) + "-" + cleaned.substring(4);
    }
}
