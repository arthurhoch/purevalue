// File: ChinaIDCardValidator.java
package io.github.arthurhoch.purevalue.cn.idcard;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Chinese ID Card.
 * Expects 18 characters: first 17 digits and a check digit (digit or 'X').
 * The check digit is computed using the weighted sum of the first 17 digits mod 11.
 */
public final class ChinaIDCardValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{17}[0-9X]");
    private static final int[] WEIGHTS = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    private static final char[] MAPPING = {'1','0','X','9','8','7','6','5','4','3','2'};

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            int digit = Character.getNumericValue(cleaned.charAt(i));
            sum += digit * WEIGHTS[i];
        }
        int remainder = sum % 11;
        char expected = MAPPING[remainder];
        char provided = cleaned.charAt(17);
        return expected == provided;
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("[^0-9X]", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 18 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid ChinaIDCard: " + raw);
        }
        return cleaned;
    }
}
