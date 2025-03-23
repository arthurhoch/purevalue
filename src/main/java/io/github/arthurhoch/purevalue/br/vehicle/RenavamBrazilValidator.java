// File: RenavamBrazilValidator.java
package io.github.arthurhoch.purevalue.br.vehicle;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Brazilian Renavam.
 * Accepts either 9 or 11 digit numbers and validates the checksum.
 * For 9-digit (old format): first 8 digits are the base and the 9th digit is the check digit.
 * For 11-digit (new format): first 10 digits are the base and the 11th digit is the check digit.
 */
public final class RenavamBrazilValidator implements ValueValidator {

    // Pattern accepting 9 or 11 digits
    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{9}(\\d{2})?");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!(cleaned.length() == 9 || cleaned.length() == 11)) return false;

        if (cleaned.length() == 9) {
            // Old format: 8-digit base + 1 check digit
            String base = cleaned.substring(0, 8);
            int checkDigit = Character.getNumericValue(cleaned.charAt(8));
            int sum = 0;
            int factor = 2;
            // Process digits from rightmost to leftmost
            for (int i = 7; i >= 0; i--) {
                int digit = Character.getNumericValue(base.charAt(i));
                sum += digit * factor;
                factor = (factor == 9) ? 2 : factor + 1;
            }
            int remainder = sum % 11;
            int expectedCheck = (remainder < 2) ? 0 : 11 - remainder;
            return expectedCheck == checkDigit;
        } else {
            // New format: 10-digit base + 1 check digit
            String base = cleaned.substring(0, 10);
            int checkDigit = Character.getNumericValue(cleaned.charAt(10));
            int sum = 0;
            int factor = 2;
            // Process digits from rightmost to leftmost
            for (int i = 9; i >= 0; i--) {
                int digit = Character.getNumericValue(base.charAt(i));
                sum += digit * factor;
                factor = (factor == 9) ? 2 : factor + 1;
            }
            int remainder = sum % 11;
            int expectedCheck = (remainder < 2) ? 0 : 11 - remainder;
            return expectedCheck == checkDigit;
        }
    }

    public boolean isFormatted(String value) {
        return value != null && isValid(clean(value));
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !(cleaned.length() == 9 || cleaned.length() == 11) || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Renavam: " + raw);
        }
        return cleaned;
    }
}
