// File: MyKadValidator.java
package io.github.arthurhoch.purevalue.my.mykad;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Malaysian MyKad.
 * Expects a 12-digit number.
 */
public final class MyKadValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{12}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return RAW_PATTERN.matcher(cleaned).matches() && !cleaned.matches("0+");
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 12 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid MyKad: " + raw);
        }
        return cleaned;
    }
}
