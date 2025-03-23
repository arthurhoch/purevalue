package io.github.arthurhoch.purevalue.book;

import io.github.arthurhoch.purevalue.core.ValueValidator;

/**
 * Validator for GTIN.
 * Accepts GTIN-8, GTIN-12, GTIN-13, or GTIN-14 (digits only).
 *
 * For GTIN-8: For positions 1 to 7 (1-indexed), use:
 *   - Odd positions: weight 3, Even positions: weight 1.
 * For GTIN-12 (UPC-A): For positions 1 to 11 (1-indexed), use:
 *   - Odd positions: weight 3, Even positions: weight 1.
 * For GTIN-13 and GTIN-14: For positions 1 to (n-1) (1-indexed), use:
 *   - Odd positions: weight 1, Even positions: weight 3.
 */
public final class GTINValidator implements ValueValidator {

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        int len = cleaned.length();
        if (!(len == 8 || len == 12 || len == 13 || len == 14)) return false;
        int sum = 0;
        if (len == 8) {
            // GTIN-8: for positions 1..7: odd positions (1,3,5,7) weight=3, even (2,4,6) weight=1.
            for (int i = 0; i < len - 1; i++) {
                int digit = Character.getNumericValue(cleaned.charAt(i));
                int weight = (((i + 1) % 2) != 0) ? 3 : 1;
                sum += digit * weight;
            }
        } else if (len == 12) {
            // UPC-A: for positions 1..11: odd positions weight=3, even weight=1.
            for (int i = 0; i < len - 1; i++) {
                int digit = Character.getNumericValue(cleaned.charAt(i));
                int weight = (((i + 1) % 2) != 0) ? 3 : 1;
                sum += digit * weight;
            }
        } else {
            // GTIN-13 and GTIN-14: use EAN-13 weighting:
            // For positions 1..(len-1): odd positions weight=1, even positions weight=3.
            for (int i = 0; i < len - 1; i++) {
                int digit = Character.getNumericValue(cleaned.charAt(i));
                int weight = (((i + 1) % 2) == 0) ? 3 : 1;
                sum += digit * weight;
            }
        }
        int checkDigit = (10 - (sum % 10)) % 10;
        int provided = Character.getNumericValue(cleaned.charAt(len - 1));
        return checkDigit == provided;
    }

    public boolean isFormatted(String value) {
        return value != null && value.trim().matches("^\\d{8}$|^\\d{12}$|^\\d{13}$|^\\d{14}$");
    }

    public String clean(String value) {
        return (value == null) ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        int len = (cleaned == null) ? 0 : cleaned.length();
        if (cleaned == null || !(len == 8 || len == 12 || len == 13 || len == 14) || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid GTIN: " + raw);
        }
        return cleaned;
    }
}
