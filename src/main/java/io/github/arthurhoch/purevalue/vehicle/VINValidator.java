// File: VINValidator.java
package io.github.arthurhoch.purevalue.vehicle;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

/**
 * Validator for Vehicle Identification Number (VIN) as per ISO 3779.
 * Validates the 17-character VIN including the check digit.
 */
public final class VINValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("[A-HJ-NPR-Z0-9]{17}");
    private static final Pattern FORMATTED_PATTERN = RAW_PATTERN;

    // Weights for each position (1-indexed; array index 0 to 16)
    private static final int[] WEIGHTS = {8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2};

    // Transliteration map (letters to numeric values)
    private static final Map<Character, Integer> TRANSLITERATION = new HashMap<>();
    static {
        TRANSLITERATION.put('A', 1);
        TRANSLITERATION.put('B', 2);
        TRANSLITERATION.put('C', 3);
        TRANSLITERATION.put('D', 4);
        TRANSLITERATION.put('E', 5);
        TRANSLITERATION.put('F', 6);
        TRANSLITERATION.put('G', 7);
        TRANSLITERATION.put('H', 8);
        // I, O, Q are not allowed
        TRANSLITERATION.put('J', 1);
        TRANSLITERATION.put('K', 2);
        TRANSLITERATION.put('L', 3);
        TRANSLITERATION.put('M', 4);
        TRANSLITERATION.put('N', 5);
        TRANSLITERATION.put('P', 7);
        TRANSLITERATION.put('R', 9);
        TRANSLITERATION.put('S', 2);
        TRANSLITERATION.put('T', 3);
        TRANSLITERATION.put('U', 4);
        TRANSLITERATION.put('V', 5);
        TRANSLITERATION.put('W', 6);
        TRANSLITERATION.put('X', 7);
        TRANSLITERATION.put('Y', 8);
        TRANSLITERATION.put('Z', 9);
        for (char c = '0'; c <= '9'; c++) {
            TRANSLITERATION.put(c, c - '0');
        }
    }

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (cleaned.length() != 17) return false;
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;

        char expectedCheckDigit = calculateCheckDigit(cleaned);
        char actualCheckDigit = cleaned.charAt(8);
        return expectedCheckDigit == actualCheckDigit;
    }

    public boolean isFormatted(String value) {
        return value != null && FORMATTED_PATTERN.matcher(value).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("[^A-Za-z0-9]", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 17) {
            throw new IllegalArgumentException("Invalid VIN: Requires 17 characters after cleaning.");
        }
        return cleaned;
    }

    private char calculateCheckDigit(String vin) {
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            char c = vin.charAt(i);
            Integer value = TRANSLITERATION.get(c);
            if (value == null) return ' '; // Should not occur due to regex validation
            sum += value * WEIGHTS[i];
        }
        int remainder = sum % 11;
        return (remainder == 10) ? 'X' : Character.forDigit(remainder, 10);
    }
}
