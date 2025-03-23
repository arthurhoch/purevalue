// File: ARCNumberValidator.java
package io.github.arthurhoch.purevalue.tw.arc;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Taiwan ARC Number (Alien Resident Certificate).
 * Expects a 10-character string: 1 letter followed by 9 digits.
 */
public final class ARCNumberValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("^[A-Z]\\d{9}$");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return RAW_PATTERN.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\s", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid ARC Number: " + raw);
        }
        return cleaned;
    }
}
