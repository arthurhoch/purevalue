package io.github.arthurhoch.purevalue.fr.siret;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for French SIRET.
 * Expects exactly 14 digits and uses the Luhn algorithm for checksum.
 */
public final class SIRETValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{14}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        int sum = 0;
        boolean alternate = false;
        for (int i = cleaned.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(cleaned.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) digit -= 9;
            }
            sum += digit;
            alternate = !alternate;
        }
        return sum % 10 == 0;
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 14 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid SIRET: " + raw);
        }
        return cleaned;
    }
}
