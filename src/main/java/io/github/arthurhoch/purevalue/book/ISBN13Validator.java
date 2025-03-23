package io.github.arthurhoch.purevalue.book;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for ISBN-13.
 * Expects exactly 13 digits.
 * Check digit is calculated as:
 *   sum = d1 + 3*d2 + d3 + 3*d4 + ... + d11 + 3*d12,
 * then check digit = (10 - (sum mod 10)) mod 10.
 */
public final class ISBN13Validator implements ValueValidator {
    private static final Pattern PATTERN = Pattern.compile("^\\d{13}$");

    @Override
    public boolean isValid(String value) {
        if(value == null) return false;
        String cleaned = clean(value);
        if (!PATTERN.matcher(cleaned).matches()) return false;
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
        if(cleaned == null || cleaned.length() != 13 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid ISBN-13: " + raw);
        }
        return cleaned;
    }
}
