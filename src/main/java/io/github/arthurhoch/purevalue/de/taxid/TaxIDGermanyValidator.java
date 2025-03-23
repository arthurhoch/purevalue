// File: TaxIDGermanyValidator.java
package io.github.arthurhoch.purevalue.de.taxid;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for German Tax Identification Number (Steuerliche Identifikationsnummer).
 * Validates an 11-digit number using the ISO 7064 mod 11,10 algorithm.
 */
public final class TaxIDGermanyValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{11}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        if (cleaned.matches("0{11}")) return false;

        int product = 10;
        for (int i = 0; i < 10; i++) {
            int digit = Character.getNumericValue(cleaned.charAt(i));
            int sum = (digit + product) % 10;
            if (sum == 0) sum = 10;
            product = (sum * 2) % 11;
        }
        int check = (11 - product) % 10;
        int providedCheck = Character.getNumericValue(cleaned.charAt(10));
        return check == providedCheck;
    }

    public boolean isFormatted(String value) {
        // No specific formatting.
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 11) {
            throw new IllegalArgumentException("Invalid German Tax ID: Requires 11 digits after cleaning.");
        }
        return cleaned;
    }
}
