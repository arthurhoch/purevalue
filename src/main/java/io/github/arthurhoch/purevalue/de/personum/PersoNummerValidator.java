// File: PersoNummerValidator.java
package io.github.arthurhoch.purevalue.de.personum;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for German PersoNummer (ID Card Number).
 * Expects 10 alphanumeric characters with the last digit as a check digit.
 * The check digit is computed using the MRZ algorithm (weights 7, 3, 1 repeating) on the first 9 characters.
 */
public final class PersoNummerValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("[A-Z0-9]{10}");
    private static final String WEIGHTS = "731"; // repeating pattern

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int weight = Character.getNumericValue(WEIGHTS.charAt(i % 3));
            int charValue = getMRZValue(cleaned.charAt(i));
            sum += charValue * weight;
        }
        int checkDigit = sum % 10;
        int providedCheck = Character.getNumericValue(cleaned.charAt(9));
        return checkDigit == providedCheck;
    }

    private int getMRZValue(char c) {
        if (Character.isDigit(c)) {
            return c - '0';
        } else if (Character.isLetter(c)) {
            return c - 'A' + 10;
        }
        return 0;
    }

    public boolean isFormatted(String value) {
        // No specific formatting.
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("[^A-Za-z0-9]", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 10) {
            throw new IllegalArgumentException("Invalid PersoNummer: Requires 10 alphanumeric characters after cleaning.");
        }
        return cleaned;
    }
}
