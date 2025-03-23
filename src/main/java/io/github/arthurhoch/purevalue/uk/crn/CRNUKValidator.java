package io.github.arthurhoch.purevalue.uk.crn;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for UK Company Registration Number (CRN).
 * Accepts either 8 digits OR 2 letters followed by 6 digits.
 */
public final class CRNUKValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("^(?:\\d{8}|[A-Z]{2}\\d{6})$");

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
        return value == null ? null : value.replaceAll("\\s", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid CRN UK: " + raw);
        }
        return cleaned;
    }
}
