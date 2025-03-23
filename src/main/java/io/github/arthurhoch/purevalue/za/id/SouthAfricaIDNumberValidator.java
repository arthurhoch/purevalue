// File: SouthAfricaIDNumberValidator.java
package io.github.arthurhoch.purevalue.za.id;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for South African ID Number.
 * Expects a 13-digit number.
 * Validation algorithm:
 *   1. Sum digits in odd positions (positions 1,3,5,7,9,11).
 *   2. Concatenate digits in even positions (positions 2,4,6,8,10,12), multiply by 2, and sum the digits of the result.
 *   3. Total = oddSum + evenSum.
 *   4. Check digit = (10 - (total mod 10)) mod 10.
 * The computed check digit must equal the 13th digit.
 */
public final class SouthAfricaIDNumberValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{13}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;

        int oddSum = 0;
        // Odd positions: indices 0,2,4,6,8,10 (positions 1,3,5,7,9,11)
        for (int i = 0; i < 12; i += 2) {
            oddSum += Character.getNumericValue(cleaned.charAt(i));
        }

        StringBuilder evenDigits = new StringBuilder();
        // Even positions: indices 1,3,5,7,9,11 (positions 2,4,6,8,10,12)
        for (int i = 1; i < 12; i += 2) {
            evenDigits.append(cleaned.charAt(i));
        }
        int evenNumber = Integer.parseInt(evenDigits.toString());
        int evenProduct = evenNumber * 2;
        int evenSum = sumOfDigits(evenProduct);

        int total = oddSum + evenSum;
        int checkDigit = (10 - (total % 10)) % 10;
        int provided = Character.getNumericValue(cleaned.charAt(12));
        return checkDigit == provided;
    }

    private int sumOfDigits(int number) {
        int sum = 0;
        String s = String.valueOf(number);
        for (char c : s.toCharArray()) {
            sum += Character.getNumericValue(c);
        }
        return sum;
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 13 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid South Africa ID Number: " + raw);
        }
        return cleaned;
    }
}
