package io.github.arthurhoch.purevalue.trade.lei;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.math.BigInteger;
import java.util.regex.Pattern;

/**
 * Validator for LEI (Legal Entity Identifier).
 * Expects exactly 20 alphanumeric characters.
 * The LEI is valid if, after converting letters to numbers (A=10,..., Z=35),
 * the resulting number mod 97 equals 1.
 */
public final class LEIValidator implements ValueValidator {
    private static final Pattern PATTERN = Pattern.compile("^[A-Z0-9]{20}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!PATTERN.matcher(cleaned).matches()) return false;
        StringBuilder numericValue = new StringBuilder();
        for (char ch : cleaned.toCharArray()) {
            if (Character.isLetter(ch)) {
                numericValue.append((int)Character.toUpperCase(ch) - 'A' + 10);
            } else {
                numericValue.append(ch);
            }
        }
        BigInteger num = new BigInteger(numericValue.toString());
        return num.mod(BigInteger.valueOf(97)).intValue() == 1;
    }

    public boolean isFormatted(String value) {
        // LEI has no special formatting; just 20 alphanumeric characters.
        return isValid(value);
    }

    public String clean(String value) {
        return (value == null) ? null : value.trim().toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 20 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid LEI: " + raw);
        }
        return cleaned;
    }
}
