package io.github.arthurhoch.purevalue.us.zip;

import io.github.arthurhoch.purevalue.core.ValueValidator;

/**
 * Validator for US ZIP Code.
 * Accepts either 5 digits or 5+4 (ZIP+4) format.
 * The cleaned value contains only digits: either 5 or 9 digits.
 */
public final class ZIPCodeUSValidator implements ValueValidator {

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return cleaned.matches("^\\d{5}$") || cleaned.matches("^\\d{9}$");
    }

    /**
     * Checks formatted value: either 5 digits or 5 digits followed by a hyphen and 4 digits.
     */
    public boolean isFormatted(String value) {
        return value != null && (value.matches("^\\d{5}$") || value.matches("^\\d{5}-\\d{4}$"));
    }

    public String clean(String value) {
        return (value == null) ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !(cleaned.length() == 5 || cleaned.length() == 9) || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid US ZIP Code: " + raw);
        }
        return cleaned;
    }
}
