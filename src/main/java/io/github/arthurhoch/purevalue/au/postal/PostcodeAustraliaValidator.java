package io.github.arthurhoch.purevalue.au.postal;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Australian Postcode.
 * Expects exactly 4 digits.
 */
public final class PostcodeAustraliaValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("^\\d{4}$");

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
        return (value == null) ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Postcode Australia: " + raw);
        }
        return cleaned;
    }
}
