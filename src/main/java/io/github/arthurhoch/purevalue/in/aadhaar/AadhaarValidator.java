// File: AadhaarValidator.java
package io.github.arthurhoch.purevalue.in.aadhaar;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Indian Aadhaar.
 * Expects 12 digits and validates using the Verhoeff algorithm.
 */
public final class AadhaarValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{12}");

    // Multiplication table for Verhoeff algorithm
    private static final int[][] d = {
            {0,1,2,3,4,5,6,7,8,9},
            {1,2,3,4,0,6,7,8,9,5},
            {2,3,4,0,1,7,8,9,5,6},
            {3,4,0,1,2,8,9,5,6,7},
            {4,0,1,2,3,9,5,6,7,8},
            {5,9,8,7,6,0,4,3,2,1},
            {6,5,9,8,7,1,0,4,3,2},
            {7,6,5,9,8,2,1,0,4,3},
            {8,7,6,5,9,3,2,1,0,4},
            {9,8,7,6,5,4,3,2,1,0}
    };

    // Permutation table for Verhoeff algorithm
    private static final int[][] p = {
            {0,1,2,3,4,5,6,7,8,9},
            {1,5,7,6,2,8,3,0,9,4},
            {5,8,0,3,7,9,6,1,4,2},
            {8,9,1,6,0,4,3,5,2,7},
            {9,4,5,3,1,2,6,8,7,0},
            {4,2,8,6,5,7,3,9,0,1},
            {2,7,9,3,8,0,6,4,1,5},
            {7,0,4,6,9,1,3,2,5,8}
    };

    // Inverse table for Verhoeff algorithm
    private static final int[] inv = {0,4,3,2,1,5,6,7,8,9};

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        return verhoeffCheck(cleaned);
    }

    private boolean verhoeffCheck(String num) {
        int c = 0;
        int[] myArray = stringToReversedIntArray(num);
        for (int i = 0; i < myArray.length; i++) {
            c = d[c][p[(i % 8)][myArray[i]]];
        }
        return c == 0;
    }

    private int[] stringToReversedIntArray(String num) {
        int[] myArray = new int[num.length()];
        for (int i = 0; i < num.length(); i++) {
            myArray[i] = Character.getNumericValue(num.charAt(num.length() - i - 1));
        }
        return myArray;
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 12 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Aadhaar: " + raw);
        }
        return cleaned;
    }
}
