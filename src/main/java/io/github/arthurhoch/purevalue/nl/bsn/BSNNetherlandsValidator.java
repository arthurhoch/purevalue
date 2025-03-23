// File: BSNNetherlandsValidator.java
package io.github.arthurhoch.purevalue.nl.bsn;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Dutch BSN (Burger Service Nummer).
 * Expects exactly 9 digits and must pass the 11-test:
 * (9*d1 + 8*d2 + 7*d3 + 6*d4 + 5*d5 + 4*d6 + 3*d7 + 2*d8 - d9) mod 11 == 0.
 */
public final class BSNNetherlandsValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{9}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            int digit = Character.getNumericValue(cleaned.charAt(i));
            sum += (9 - i) * digit;
        }
        int lastDigit = Character.getNumericValue(cleaned.charAt(8));
        sum -= lastDigit;
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
        if (cleaned == null || cleaned.length() != 9 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid BSN: " + raw);
        }
        return cleaned;
    }
}
