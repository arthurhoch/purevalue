package io.github.arthurhoch.purevalue.be.vat;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Belgian VAT Number.
 * Expects exactly 10 digits.
 * The check digits are computed as: expected = 97 - (base mod 97) for the first 8 digits.
 * If expected equals 97, then it is represented as 00.
 */
public final class VATNumberBelgiumValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{10}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        String baseStr = cleaned.substring(0, 8);
        int base = Integer.parseInt(baseStr);
        int remainder = base % 97;
        int expected = 97 - remainder;
        if (expected == 97) expected = 0;
        String expectedStr = String.format("%02d", expected);
        String provided = cleaned.substring(8);
        return expectedStr.equals(provided);
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 10 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid VAT Number Belgium: " + raw);
        }
        return cleaned;
    }
}
