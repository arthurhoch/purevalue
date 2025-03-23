// File: NumeroSegurancaSocialPTValidator.java
package io.github.arthurhoch.purevalue.pt.niss;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Portuguese Social Security Number (NISS).
 * Expects an 11-digit number.
 * The check digit is computed by multiplying the first 10 digits by the weights
 * [29, 23, 19, 17, 13, 11, 7, 5, 3, 2], summing the products, then taking the remainder mod 10,
 * and finally setting the check digit to 0 if the remainder is 0 or to (10 - remainder) otherwise.
 */
public final class NumeroSegurancaSocialPTValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{11}");
    private static final int[] WEIGHTS = {29, 23, 19, 17, 13, 11, 7, 5, 3, 2};

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            int digit = Character.getNumericValue(cleaned.charAt(i));
            sum += digit * WEIGHTS[i];
        }
        int remainder = sum % 10;
        int check = (remainder == 0) ? 0 : (10 - remainder);
        int providedCheck = Character.getNumericValue(cleaned.charAt(10));
        return check == providedCheck;
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 11 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid NumeroSegurancaSocialPT: " + raw);
        }
        return cleaned;
    }
}
