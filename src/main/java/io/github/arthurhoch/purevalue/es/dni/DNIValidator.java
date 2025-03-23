// File: DNIValidator.java
package io.github.arthurhoch.purevalue.es.dni;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Spanish DNI (Documento Nacional de Identidad).
 * Expects 8 digits followed by a letter. The letter is determined by the remainder
 * of the numeric part divided by 23 using the sequence "TRWAGMYFPDXBNJZSQVHLCKE".
 */
public final class DNIValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{8}[A-Z]");
    private static final String LETTERS = "TRWAGMYFPDXBNJZSQVHLCKE";

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        String numberPart = cleaned.substring(0, 8);
        char letter = cleaned.charAt(8);
        int num = Integer.parseInt(numberPart);
        char expectedLetter = LETTERS.charAt(num % 23);
        return letter == expectedLetter;
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
            throw new IllegalArgumentException("Invalid DNI: " + raw);
        }
        return cleaned;
    }
}
