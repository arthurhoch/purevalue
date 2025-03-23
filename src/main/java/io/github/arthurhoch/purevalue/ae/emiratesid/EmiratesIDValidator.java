// File: EmiratesIDValidator.java
package io.github.arthurhoch.purevalue.ae.emiratesid;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for UAE Emirates ID.
 * Expects a 15-digit number (non‑all‑zero).
 */
public final class EmiratesIDValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{15}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        return !cleaned.matches("0+");
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 15 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Emirates ID: " + raw);
        }
        return cleaned;
    }
}
