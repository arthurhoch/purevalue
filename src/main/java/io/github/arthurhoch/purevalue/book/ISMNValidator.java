package io.github.arthurhoch.purevalue.book;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for ISMN (International Standard Music Number).
 * Modern ISMN is a 13-digit number starting with "9790" and follows the EAN-13 algorithm.
 */
public final class ISMNValidator implements ValueValidator {
    private static final Pattern PATTERN = Pattern.compile("^9790\\d{9}$");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!PATTERN.matcher(cleaned).matches()) return false;
        // Use the EAN-13 checksum algorithm.
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(cleaned.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        int checkDigit = (10 - (sum % 10)) % 10;
        int provided = Character.getNumericValue(cleaned.charAt(12));
        return checkDigit == provided;
    }

    public boolean isFormatted(String value) {
        return false;
    }

    public String clean(String value) {
        return (value == null) ? null : value.replaceAll("[\\s-]", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 13 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid ISMN: " + raw);
        }
        return cleaned;
    }
}
