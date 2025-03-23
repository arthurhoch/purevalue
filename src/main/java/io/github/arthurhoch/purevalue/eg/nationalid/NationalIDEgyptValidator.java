// File: NationalIDEgyptValidator.java
package io.github.arthurhoch.purevalue.eg.nationalid;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Egyptian National ID.
 * Expects a 14-digit number.
 */
public final class NationalIDEgyptValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{14}");

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
        if (cleaned == null || cleaned.length() != 14 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid National IDEgypt: " + raw);
        }
        return cleaned;
    }
}
