package io.github.arthurhoch.purevalue.trade.isin;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for ISIN (International Securities Identification Number).
 * Format: Two letters, nine alphanumeric characters, and one check digit.
 * The check digit is computed using a modified Luhn algorithm after converting letters (A=10, …, Z=35).
 */
public final class ISINValidator implements ValueValidator {

    private static final Pattern PATTERN = Pattern.compile("^[A-Z]{2}[A-Z0-9]{9}\\d$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!PATTERN.matcher(cleaned).matches()) return false;
        // Convert first 11 characters: letters -> numbers
        StringBuilder converted = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            char ch = cleaned.charAt(i);
            if (Character.isLetter(ch)) {
                int num = Character.toUpperCase(ch) - 'A' + 10;
                converted.append(num);
            } else {
                converted.append(ch);
            }
        }
        // Append the check digit (last character) for comparison later
        int providedCheck = Character.getNumericValue(cleaned.charAt(11));
        // Now apply Luhn algorithm on the resulting string (converted digits)
        String digits = converted.toString();
        int sum = 0;
        boolean alternate = true; // start from rightmost digit of the converted string
        for (int i = digits.length() - 1; i >= 0; i--) {
            int d = Character.getNumericValue(digits.charAt(i));
            if (alternate) {
                d *= 2;
                if (d > 9) {
                    d = (d / 10) + (d % 10);
                }
            }
            sum += d;
            alternate = !alternate;
        }
        int computedCheck = (10 - (sum % 10)) % 10;
        return computedCheck == providedCheck;
    }

    public String clean(String value) {
        return (value == null) ? null : value.replaceAll("[\\s-]", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 12 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid ISIN: " + raw);
        }
        return cleaned;
    }

    public boolean isFormatted(String value) {
        // No special formatted variant enforced.
        return isValid(value);
    }
}
