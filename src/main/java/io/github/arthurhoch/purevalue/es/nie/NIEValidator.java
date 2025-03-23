// File: NIEValidator.java
package io.github.arthurhoch.purevalue.es.nie;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Spanish NIE (Número de Identificación de Extranjero).
 * Expects a letter (X, Y, or Z), followed by 7 digits and a final check letter.
 * The initial letter is converted (X→0, Y→1, Z→2) and then the check letter is computed
 * using the same algorithm as for DNI.
 */
public final class NIEValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("[XYZ]\\d{7}[A-Z]");
    private static final String LETTERS = "TRWAGMYFPDXBNJZSQVHLCKE";

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;

        char firstChar = cleaned.charAt(0);
        String numberPart = "";
        switch(firstChar) {
            case 'X': numberPart = "0" + cleaned.substring(1, 8); break;
            case 'Y': numberPart = "1" + cleaned.substring(1, 8); break;
            case 'Z': numberPart = "2" + cleaned.substring(1, 8); break;
            default: return false;
        }
        int num = Integer.parseInt(numberPart);
        char expectedLetter = LETTERS.charAt(num % 23);
        char providedLetter = cleaned.charAt(8);
        return expectedLetter == providedLetter;
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\s", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 9 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid NIE: " + raw);
        }
        return cleaned;
    }
}
