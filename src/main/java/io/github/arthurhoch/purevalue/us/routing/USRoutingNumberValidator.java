// File: USRoutingNumberValidator.java
package io.github.arthurhoch.purevalue.us.routing;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for US Routing Number.
 * Validates the number using the ABA checksum algorithm.
 */
public final class USRoutingNumberValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{9}");
    private static final Pattern FORMATTED_PATTERN = Pattern.compile("\\d{9}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        if (cleaned.matches("0{9}")) return false; // Reject all-zero routing numbers

        int sum = 0;
        // Apply weights: 3, 7, 1 repeating for each digit.
        int[] weights = {3, 7, 1, 3, 7, 1, 3, 7, 1};
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(cleaned.charAt(i));
            sum += digit * weights[i];
        }
        return sum % 10 == 0;
    }

    public boolean isFormatted(String value) {
        return value != null && FORMATTED_PATTERN.matcher(value).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 9) {
            throw new IllegalArgumentException("Invalid US Routing Number: Requires 9 digits after cleaning.");
        }
        return cleaned;
    }
}
