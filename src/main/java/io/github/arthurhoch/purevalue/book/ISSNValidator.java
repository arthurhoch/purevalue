package io.github.arthurhoch.purevalue.book;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for ISSN.
 * Expects exactly 8 characters: first 7 digits and a check digit (digit or 'X').
 * Check digit is computed as:
 *   sum = 8*d1 + 7*d2 + ... + 2*d7 + d8 (with 'X' representing 10);
 * valid if sum mod 11 == 0.
 */
public final class ISSNValidator implements ValueValidator {
    private static final Pattern PATTERN = Pattern.compile("^\\d{7}[\\dX]$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value) {
        if(value == null) return false;
        String cleaned = clean(value);
        if (!PATTERN.matcher(cleaned).matches()) return false;
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            char ch = cleaned.charAt(i);
            int digit = (i == 7 && (ch == 'X' || ch == 'x')) ? 10 : Character.getNumericValue(ch);
            sum += (8 - i) * digit;
        }
        return sum % 11 == 0;
    }

    public boolean isFormatted(String value) {
        return false;
    }

    public String clean(String value) {
        return (value == null) ? null : value.replaceAll("[\\s-]", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if(cleaned == null || cleaned.length() != 8 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid ISSN: " + raw);
        }
        return cleaned;
    }
}
