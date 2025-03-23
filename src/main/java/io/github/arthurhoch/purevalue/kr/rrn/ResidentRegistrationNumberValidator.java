// File: ResidentRegistrationNumberValidator.java
package io.github.arthurhoch.purevalue.kr.rrn;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for South Korean Resident Registration Number (RRN).
 * Expects a 13-digit number.
 * The check digit is computed by:
 *   - Multiplying the first 12 digits by weights [2,3,4,5,6,7,8,9,2,3,4,5].
 *   - Summing the products.
 *   - Calculating: (11 - (sum mod 11)) mod 10.
 * This value must equal the 13th digit.
 */
public final class ResidentRegistrationNumberValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{13}");
    private static final int[] WEIGHTS = {2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5};

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(cleaned.charAt(i));
            sum += digit * WEIGHTS[i];
        }
        int remainder = sum % 11;
        int checkDigit = (11 - remainder) % 10;
        int provided = Character.getNumericValue(cleaned.charAt(12));
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
        if (cleaned == null || cleaned.length() != 13 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Resident Registration Number: " + raw);
        }
        return cleaned;
    }
}
