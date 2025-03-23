// File: PersonalNumberSwedenValidator.java
package io.github.arthurhoch.purevalue.se.personnummer;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Swedish Personal Number (Personnummer).
 * Expects a 10-digit number.
 * Uses the Luhn algorithm on the first 9 digits to compute the check digit.
 */
public final class PersonalNumberSwedenValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{10}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(cleaned.charAt(i));
            if (i % 2 == 1) {
                digit *= 2;
                if (digit > 9) digit -= 9;
            }
            sum += digit;
        }
        int checkDigit = (10 - (sum % 10)) % 10;
        int provided = Character.getNumericValue(cleaned.charAt(9));
        return checkDigit == provided;
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
            throw new IllegalArgumentException("Invalid Personal Number Sweden: " + raw);
        }
        return cleaned;
    }
}
