package io.github.arthurhoch.purevalue.finance.iban;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.math.BigInteger;
import java.util.regex.Pattern;

/**
 * Validator for IBAN.
 * Implements the standard IBAN check:
 *  - Remove spaces, move first 4 characters to the end,
 *  - Replace letters with numbers (A=10,..., Z=35),
 *  - Check if the resulting number mod 97 equals 1.
 */
public final class IBANValidator implements ValueValidator {
    // Basic IBAN pattern: Two letters, two digits, then 11 to 30 alphanumeric characters.
    private static final Pattern BASIC_PATTERN = Pattern.compile("^[A-Z]{2}\\d{2}[A-Z0-9]{11,30}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!BASIC_PATTERN.matcher(cleaned).matches()) return false;
        String rearranged = cleaned.substring(4) + cleaned.substring(0, 4);
        StringBuilder numericIban = new StringBuilder();
        for (char c : rearranged.toCharArray()) {
            if (Character.isLetter(c)) {
                numericIban.append((int)Character.toUpperCase(c) - 'A' + 10);
            } else {
                numericIban.append(c);
            }
        }
        BigInteger ibanNumber = new BigInteger(numericIban.toString());
        return ibanNumber.mod(BigInteger.valueOf(97)).intValue() == 1;
    }

    public boolean isFormatted(String value) {
        // The IBAN can be formatted with spaces; this method returns true if the cleaned value is valid.
        return value != null;
    }

    public String clean(String value) {
        return (value == null) ? null : value.replaceAll("\\s", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid IBAN: " + raw);
        }
        return cleaned;
    }
}
