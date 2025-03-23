package io.github.arthurhoch.purevalue.internet.imei;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for IMEI (International Mobile Equipment Identity).
 * Expects a 15-digit number. The last digit is a Luhn check digit computed from the first 14 digits.
 */
public final class IMEIValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{15}");

    @Override
    public boolean isValid(String value) {
        if(value == null) return false;
        String cleaned = clean(value);
        if(!RAW_PATTERN.matcher(cleaned).matches()) return false;
        int sum = 0;
        // Compute Luhn check on first 14 digits.
        for (int i = 0; i < 14; i++) {
            int digit = Character.getNumericValue(cleaned.charAt(i));
            if (i % 2 == 1) { // double every digit in odd index (0-based)
                digit *= 2;
                if(digit > 9) digit -= 9;
            }
            sum += digit;
        }
        int checkDigit = (10 - (sum % 10)) % 10;
        int provided = Character.getNumericValue(cleaned.charAt(14));
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
        if(cleaned == null || cleaned.length() != 15 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid IMEI: " + raw);
        }
        return cleaned;
    }
}
