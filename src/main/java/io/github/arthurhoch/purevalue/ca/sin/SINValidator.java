// File: SINValidator.java
package io.github.arthurhoch.purevalue.ca.sin;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Canadian Social Insurance Number (SIN).
 * Expects 9 digits and uses the Luhn algorithm for validation.
 */
public final class SINValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{9}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        return luhnCheck(cleaned);
    }

    private boolean luhnCheck(String number) {
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {
            int digit = Character.getNumericValue(number.charAt(i));
            if ((i % 2) == 1) { // double every second digit
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }
        return (sum % 10) == 0;
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
            throw new IllegalArgumentException("Invalid SIN: " + raw);
        }
        return cleaned;
    }
}
