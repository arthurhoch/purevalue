package io.github.arthurhoch.purevalue.it.partitaiva;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Italian Partita IVA.
 * Expects exactly 11 digits.
 * Check digit is computed on the first 10 digits:
 * - For digits in odd positions (1st,3rd,5th,7th,9th) multiply by 2 (subtract 9 if > 9);
 * - Sum with digits in even positions.
 * - Control digit = (10 - (sum mod 10)) mod 10.
 */
public final class PartitaIVAValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{11}");

    @Override
    public boolean isValid(String value) {
        if(value == null) return false;
        String cleaned = clean(value);
        if(!RAW_PATTERN.matcher(cleaned).matches()) return false;
        int sum = 0;
        for (int i = 0; i < 10; i++){
            int digit = Character.getNumericValue(cleaned.charAt(i));
            if((i % 2) == 0){ // positions 1,3,5,7,9 (0-indexed even)
                int prod = digit * 2;
                if(prod > 9) prod -= 9;
                sum += prod;
            } else {
                sum += digit;
            }
        }
        int checkDigit = (10 - (sum % 10)) % 10;
        int provided = Character.getNumericValue(cleaned.charAt(10));
        return checkDigit == provided;
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if(cleaned == null || cleaned.length() != 11 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Partita IVA: " + raw);
        }
        return cleaned;
    }
}
