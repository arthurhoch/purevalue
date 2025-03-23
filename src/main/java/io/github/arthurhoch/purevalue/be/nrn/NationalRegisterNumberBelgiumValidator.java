// File: NationalRegisterNumberBelgiumValidator.java
package io.github.arthurhoch.purevalue.be.nrn;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Belgian National Register Number.
 * Expects an 11-digit number.
 * The check digits are computed as: 97 - (first 9 digits mod 97).
 * If the result equals 97, it is represented as 00.
 */
public final class NationalRegisterNumberBelgiumValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{11}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        int base = Integer.parseInt(cleaned.substring(0, 9));
        int expected = 97 - (base % 97);
        if (expected == 97) expected = 0;
        int provided = Integer.parseInt(cleaned.substring(9, 11));
        return expected == provided;
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 11 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid National Register Number Belgium: " + raw);
        }
        return cleaned;
    }
}
