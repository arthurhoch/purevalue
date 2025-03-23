// File: MyNumberJapanValidator.java
package io.github.arthurhoch.purevalue.jp.mynumber;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Japanese MyNumber (Individual Number).
 * Expects a 12-digit number.
 * The check digit is calculated by multiplying the first 11 digits with weights (cycling from 2 to 7),
 * summing the products, then computing (9 - (sum mod 9)) mod 9.
 */
public final class MyNumberJapanValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{12}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        int sum = 0;
        int weight = 2;
        // Process first 11 digits from right to left
        for (int i = 10; i >= 0; i--) {
            int digit = Character.getNumericValue(cleaned.charAt(i));
            sum += digit * weight;
            weight++;
            if (weight > 7) weight = 2;
        }
        int remainder = sum % 9;
        int checkDigit = (9 - remainder) % 9;
        int provided = Character.getNumericValue(cleaned.charAt(11));
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
        if (cleaned == null || cleaned.length() != 12 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid MyNumberJapan: " + raw);
        }
        return cleaned;
    }
}
