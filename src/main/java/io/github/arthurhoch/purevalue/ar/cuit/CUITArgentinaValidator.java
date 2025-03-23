// File: CUITArgentinaValidator.java
package io.github.arthurhoch.purevalue.ar.cuit;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Argentine CUIT (Clave Única de Identificación Tributaria).
 * Expects 11 digits and validates using a weighting algorithm.
 */
public final class CUITArgentinaValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{11}");
    private static final int[] WEIGHTS = {5,4,3,2,7,6,5,4,3,2};

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            int digit = Character.getNumericValue(cleaned.charAt(i));
            sum += digit * WEIGHTS[i];
        }
        int remainder = sum % 11;
        int expected = (remainder == 0) ? 0 : (remainder == 1 ? 9 : 11 - remainder);
        int provided = Character.getNumericValue(cleaned.charAt(10));
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
            throw new IllegalArgumentException("Invalid CUIT: " + raw);
        }
        return cleaned;
    }
}
