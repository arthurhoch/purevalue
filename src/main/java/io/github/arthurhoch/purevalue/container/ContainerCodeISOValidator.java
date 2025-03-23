package io.github.arthurhoch.purevalue.container;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Validator for ISO 6346 Container Code.
 * Expected format: 4 letters (owner code), 6 digits (serial), and 1 digit (check digit) — total 11 characters.
 */
public final class ContainerCodeISOValidator implements ValueValidator {
    private static final Pattern PATTERN = Pattern.compile("^[A-Z]{4}\\d{7}$", Pattern.CASE_INSENSITIVE);

    private static final Map<Character, Integer> LETTER_VALUES = new HashMap<>();
    static {
        // ISO 6346 conversion table: A=10, B=12, C=13, D=14, E=15, F=16, G=17, H=18, I=19,
        // J=20, K=21, L=23, M=24, N=25, O=26, P=27, Q=28, R=29, S=30, T=31, U=32, V=34, W=35, X=36, Y=37, Z=38.
        LETTER_VALUES.put('A', 10);
        LETTER_VALUES.put('B', 12);
        LETTER_VALUES.put('C', 13);
        LETTER_VALUES.put('D', 14);
        LETTER_VALUES.put('E', 15);
        LETTER_VALUES.put('F', 16);
        LETTER_VALUES.put('G', 17);
        LETTER_VALUES.put('H', 18);
        LETTER_VALUES.put('I', 19);
        LETTER_VALUES.put('J', 20);
        LETTER_VALUES.put('K', 21);
        LETTER_VALUES.put('L', 23);
        LETTER_VALUES.put('M', 24);
        LETTER_VALUES.put('N', 25);
        LETTER_VALUES.put('O', 26);
        LETTER_VALUES.put('P', 27);
        LETTER_VALUES.put('Q', 28);
        LETTER_VALUES.put('R', 29);
        LETTER_VALUES.put('S', 30);
        LETTER_VALUES.put('T', 31);
        LETTER_VALUES.put('U', 32);
        LETTER_VALUES.put('V', 34);
        LETTER_VALUES.put('W', 35);
        LETTER_VALUES.put('X', 36);
        LETTER_VALUES.put('Y', 37);
        LETTER_VALUES.put('Z', 38);
    }

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!PATTERN.matcher(cleaned).matches()) return false;
        int sum = 0;
        // For positions 0 to 9 (first 10 characters)
        for (int i = 0; i < 10; i++) {
            char ch = cleaned.charAt(i);
            int numericValue;
            if (Character.isDigit(ch)) {
                numericValue = Character.getNumericValue(ch);
            } else {
                numericValue = LETTER_VALUES.getOrDefault(ch, -1);
                if (numericValue == -1) return false;
            }
            int weight = (int) Math.pow(2, i);
            sum += numericValue * weight;
        }
        int remainder = sum % 11;
        int computedCheck = (11 - remainder) % 10;
        int providedCheck = Character.getNumericValue(cleaned.charAt(10));
        return computedCheck == providedCheck;
    }

    public String clean(String value) {
        return (value == null) ? null : value.replaceAll("[\\s-]", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 11 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Container Code ISO: " + raw);
        }
        return cleaned;
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }
}
