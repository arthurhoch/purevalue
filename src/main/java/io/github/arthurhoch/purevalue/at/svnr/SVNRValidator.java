// File: SVNRValidator.java
package io.github.arthurhoch.purevalue.at.svnr;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Austrian SVNR (Sozialversicherungsnummer).
 * Expects exactly 10 digits.
 * The check digit is computed as:
 *   sum = (1*d1 + 2*d2 + ... + 9*d9) mod 10.
 */
public final class SVNRValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{10}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(cleaned.charAt(i));
            sum += (i + 1) * digit;
        }
        int check = sum % 10;
        int provided = Character.getNumericValue(cleaned.charAt(9));
        return check == provided;
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 10 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid SVNR: " + raw);
        }
        return cleaned;
    }
}
