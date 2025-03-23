package io.github.arthurhoch.purevalue.internet.meid;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for MEID (Mobile Equipment Identifier).
 * Expects a 14-character hexadecimal string.
 */
public final class MEIDValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("[0-9A-Fa-f]{14}");

    @Override
    public boolean isValid(String value) {
        if(value == null) return false;
        String cleaned = clean(value);
        return RAW_PATTERN.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("[^0-9A-Fa-f]", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if(cleaned == null || cleaned.length() != 14 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid MEID: " + raw);
        }
        return cleaned;
    }
}
