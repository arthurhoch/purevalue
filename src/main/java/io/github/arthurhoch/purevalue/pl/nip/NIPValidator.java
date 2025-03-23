package io.github.arthurhoch.purevalue.pl.nip;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Polish NIP (Numer Identyfikacji Podatkowej).
 * Expects exactly 10 digits.
 * Checksum: (6*d1 + 5*d2 + 7*d3 + 2*d4 + 3*d5 + 4*d6 + 5*d7 + 6*d8 + 7*d9) mod 11 must equal d10.
 */
public final class NIPValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{10}");
    private static final int[] WEIGHTS = {6,5,7,2,3,4,5,6,7};

    @Override
    public boolean isValid(String value) {
        if(value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(cleaned.charAt(i));
            sum += digit * WEIGHTS[i];
        }
        int check = sum % 11;
        int provided = Character.getNumericValue(cleaned.charAt(9));
        return check == provided;
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return (value == null) ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 10 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid NIP (Poland): " + raw);
        }
        return cleaned;
    }
}
