// File: RUTChileValidator.java
package io.github.arthurhoch.purevalue.cl.rut;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Chilean RUT (Rol Único Tributario).
 * Expects a RUT with a body of 7 or 8 digits and a check digit that is a number or 'K'.
 * Uses the modulus 11 algorithm with factors cycling from 2 to 7.
 */
public final class RUTChileValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("^\\d{7,8}[0-9K]$");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        String body = cleaned.substring(0, cleaned.length() - 1);
        char providedCheck = cleaned.charAt(cleaned.length() - 1);
        int sum = 0;
        int factor = 2;
        for (int i = body.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(body.charAt(i));
            sum += digit * factor;
            factor = (factor == 7) ? 2 : factor + 1;
        }
        int remainder = 11 - (sum % 11);
        char expectedCheck;
        if (remainder == 11) {
            expectedCheck = '0';
        } else if (remainder == 10) {
            expectedCheck = 'K';
        } else {
            expectedCheck = Character.forDigit(remainder, 10);
        }
        return expectedCheck == providedCheck;
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("[^0-9Kk]", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !RAW_PATTERN.matcher(cleaned).matches() || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid RUTChile: " + raw);
        }
        return cleaned;
    }
}
