// File: NIFPortugalValidator.java
package io.github.arthurhoch.purevalue.pt.nif;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Portuguese NIF (Número de Identificação Fiscal).
 * Expects a 9-digit number. The check digit is calculated by multiplying the first 8 digits
 * with weights from 9 to 2, taking the remainder mod 11, and then computing 0 if the remainder is less than 2,
 * or 11 minus the remainder otherwise.
 */
public final class NIFPortugalValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{9}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            int digit = Character.getNumericValue(cleaned.charAt(i));
            sum += digit * (9 - i);
        }
        int remainder = sum % 11;
        int check = (remainder < 2) ? 0 : 11 - remainder;
        int providedCheck = Character.getNumericValue(cleaned.charAt(8));
        return check == providedCheck;
    }

    public boolean isFormatted(String value) {
        // No specific formatting.
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 9 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid NIFPortugal: " + raw);
        }
        return cleaned;
    }
}
