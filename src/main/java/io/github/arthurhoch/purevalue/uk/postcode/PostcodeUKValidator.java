package io.github.arthurhoch.purevalue.uk.postcode;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for UK Postcode.
 * Uses a simplified regex covering most valid formats.
 */
public final class PostcodeUKValidator implements ValueValidator {
    // Simplified pattern; covers most UK postcodes.
    private static final Pattern RAW_PATTERN = Pattern.compile("^(GIR 0AA|[A-Z]{1,2}\\d[A-Z\\d]? ?\\d[ABD-HJLNP-UW-Z]{2})$", Pattern.CASE_INSENSITIVE);

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
        return (value == null) ? null : value.trim().toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid UK Postcode: " + raw);
        }
        return cleaned;
    }
}
