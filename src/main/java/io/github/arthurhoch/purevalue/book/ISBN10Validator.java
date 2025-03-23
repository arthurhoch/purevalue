package io.github.arthurhoch.purevalue.book;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for ISBN-10.
 * Expects 10 characters: first 9 digits, and a check digit which may be a digit or 'X'.
 * The checksum is calculated as:
 *   sum = 10*d1 + 9*d2 + ... + 2*d9 + 1*d10;
 * valid if sum mod 11 == 0.
 */
public final class ISBN10Validator implements ValueValidator {
    private static final Pattern PATTERN = Pattern.compile("^\\d{9}[\\dX]$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!PATTERN.matcher(cleaned).matches()) return false;
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            char ch = cleaned.charAt(i);
            int digit = (i == 9 && (ch == 'X' || ch == 'x')) ? 10 : Character.getNumericValue(ch);
            sum += (10 - i) * digit;
        }
        return sum % 11 == 0;
    }

    public boolean isFormatted(String value) {
        // For simplicity, we do not enforce a specific formatted pattern.
        return false;
    }

    public String clean(String value) {
        return (value == null) ? null : value.replaceAll("[\\s-]", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 10 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid ISBN-10: " + raw);
        }
        return cleaned;
    }
}
