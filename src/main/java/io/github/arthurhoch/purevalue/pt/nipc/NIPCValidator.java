package io.github.arthurhoch.purevalue.pt.nipc;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.HashSet;

/**
 * Validator for Portuguese NIPC.
 * Expects exactly 9 digits.
 * Validates using the standard Portuguese NIF algorithm and requires that the first digit is one of 5,6,8, or 9.
 */
public final class NIPCValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{9}");
    private static final Set<Character> ALLOWED_FIRST_DIGITS = new HashSet<>(Set.of('5', '6', '8', '9'));

    @Override
    public boolean isValid(String value) {
        if(value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        // Check that the first digit is one of the allowed ones
        if (!ALLOWED_FIRST_DIGITS.contains(cleaned.charAt(0))) return false;
        // Calculate the checksum: Multiply the first 8 digits by weights 9,8,...,2, then compute:
        // remainder = (sum mod 11), check digit = (remainder < 2 ? 0 : 11 - remainder)
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            int digit = Character.getNumericValue(cleaned.charAt(i));
            sum += digit * (9 - i);
        }
        int remainder = sum % 11;
        int expectedCheck = (remainder < 2) ? 0 : 11 - remainder;
        int providedCheck = Character.getNumericValue(cleaned.charAt(8));
        return expectedCheck == providedCheck;
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if(cleaned == null || cleaned.length() != 9 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid NIPC: " + raw);
        }
        return cleaned;
    }
}
