package io.github.arthurhoch.purevalue.phone;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for international phone numbers in E.164 format.
 * E.164 requires a leading '+' followed by 2 to 15 digits (the first digit after '+' must be nonzero).
 */
public final class PhoneNumberValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("^\\+[1-9]\\d{1,14}$");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return RAW_PATTERN.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }

    /**
     * Cleans the input by trimming and removing common formatting characters such as spaces, hyphens, and parentheses.
     */
    public String clean(String value) {
        if (value == null) return null;
        // Remove spaces, dashes, and parentheses
        return value.trim().replaceAll("[\\s\\-()]", "");
    }

    /**
     * Returns the cleaned value if valid; otherwise throws an exception.
     */
    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Phone Number (E.164): " + raw);
        }
        return cleaned;
    }
}
