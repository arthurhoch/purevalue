// File: FoedselsnummerValidator.java
package io.github.arthurhoch.purevalue.no.fnr;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Norwegian Foedselsnummer.
 * Expects an 11-digit number.
 * First check digit: calculated from first 9 digits using weights {3,7,6,1,8,9,4,5,2}.
 * Second check digit: calculated from first 10 digits using weights {5,4,3,2,7,6,5,4,3,2}.
 */
public final class FoedselsnummerValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{11}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;

        int[] weights1 = {3,7,6,1,8,9,4,5,2};
        int sum1 = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(cleaned.charAt(i));
            sum1 += digit * weights1[i];
        }
        int remainder1 = sum1 % 11;
        int check1 = 11 - remainder1;
        if (check1 == 11) check1 = 0;
        if (check1 == 10) return false;
        if (check1 != Character.getNumericValue(cleaned.charAt(9))) return false;

        int[] weights2 = {5,4,3,2,7,6,5,4,3,2};
        int sum2 = 0;
        for (int i = 0; i < 10; i++) {
            int digit = Character.getNumericValue(cleaned.charAt(i));
            sum2 += digit * weights2[i];
        }
        int remainder2 = sum2 % 11;
        int check2 = 11 - remainder2;
        if (check2 == 11) check2 = 0;
        if (check2 == 10) return false;
        return check2 == Character.getNumericValue(cleaned.charAt(10));
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
            throw new IllegalArgumentException("Invalid Foedselsnummer: " + raw);
        }
        return cleaned;
    }
}
