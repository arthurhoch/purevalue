// File: TFNValidator.java
package io.github.arthurhoch.purevalue.au.tfn;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Australian Tax File Number (TFN).
 * Accepts 8 or 9 digit numbers.
 * Validates using weighting factors and mod 11 check.
 */
public final class TFNValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{8,9}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;

        int[] weights;
        if (cleaned.length() == 9) {
            weights = new int[]{1, 4, 3, 7, 5, 8, 6, 9, 10};
        } else {
            weights = new int[]{10, 7, 8, 4, 6, 3, 2, 5};
        }
        int sum = 0;
        for (int i = 0; i < cleaned.length(); i++) {
            int digit = Character.getNumericValue(cleaned.charAt(i));
            sum += digit * weights[i];
        }
        return sum % 11 == 0;
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !(cleaned.length() == 8 || cleaned.length() == 9) || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid TFN: " + raw);
        }
        return cleaned;
    }
}
