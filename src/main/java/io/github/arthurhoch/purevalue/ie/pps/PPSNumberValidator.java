// File: PPSNumberValidator.java
package io.github.arthurhoch.purevalue.ie.pps;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Irish PPS Number (Personal Public Service Number).
 * Expects exactly 8 characters: 7 digits followed by 1 letter.
 * The check letter is computed as:
 *   sum = 8*d1 + 7*d2 + 6*d3 + 5*d4 + 4*d5 + 3*d6 + 2*d7,
 *   remainder = sum mod 23,
 *   expected letter = lookup.charAt(remainder),
 *   with lookup = "WABCDEFGHIKLMNPQRSTUVXY".
 */
public final class PPSNumberValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{7}[A-Z]");
    private static final String LOOKUP = "WABCDEFGHIKLMNPQRSTUVXY"; // length 23

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        int sum = 0;
        int[] weights = {8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 7; i++) {
            int digit = Character.getNumericValue(cleaned.charAt(i));
            sum += weights[i] * digit;
        }
        int remainder = sum % 23;
        char expected = LOOKUP.charAt(remainder);
        char provided = cleaned.charAt(7);
        return expected == provided;
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\s", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 8 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid PPS Number: " + raw);
        }
        return cleaned;
    }
}
