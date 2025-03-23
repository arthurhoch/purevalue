// File: AHVNumberSwitzerlandValidator.java
package io.github.arthurhoch.purevalue.ch.ahv;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Swiss AHV Number.
 * Expects a 13-digit number starting with "756".
 * The check digit is computed as (11 - (first12 digits mod 11)) mod 11.
 * If the result equals 10, the number is invalid.
 */
public final class AHVNumberSwitzerlandValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("756\\d{10}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        String baseStr = cleaned.substring(0, 12);
        long base = Long.parseLong(baseStr);
        long remainder = base % 11;
        int expected = (int)((11 - remainder) % 11);
        if (expected == 10) return false;
        int provided = Character.getNumericValue(cleaned.charAt(12));
        return expected == provided;
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("[^\\d]", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 13 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid AHV Number Switzerland: " + raw);
        }
        return cleaned;
    }
}