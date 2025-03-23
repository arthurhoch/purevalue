package io.github.arthurhoch.purevalue.nz.ird;

import io.github.arthurhoch.purevalue.core.ValueValidator;

import java.util.regex.Pattern;

public final class IRDNumberValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{8,9}");
    private static final int[] PRIMARY_WEIGHTS = {3, 2, 7, 6, 5, 4, 3};
    private static final int[] ALTERNATE_WEIGHTS = {7, 4, 3, 2, 5, 2, 7};

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;

        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;

        // Remove zero à esquerda se tiver 9 dígitos começando com 0
        if (cleaned.length() == 9 && cleaned.charAt(0) == '0') {
            cleaned = cleaned.substring(1);
        }

        if (cleaned.length() != 8) return false;

        int[] digits = cleaned.chars().map(c -> c - '0').toArray();
        int[] baseDigits = new int[7];
        System.arraycopy(digits, 0, baseDigits, 0, 7);
        int providedCheckDigit = digits[7];

        int calculated = calculateCheckDigit(baseDigits, PRIMARY_WEIGHTS);
        if (calculated == 10) {
            calculated = calculateCheckDigit(baseDigits, ALTERNATE_WEIGHTS);
        }

        return calculated != 10 && calculated == providedCheckDigit;
    }

    private int calculateCheckDigit(int[] digits, int[] weights) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += digits[i] * weights[i];
        }
        int mod = sum % 11;
        return (mod == 0) ? 0 : (11 - mod);
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !(cleaned.length() == 8 || cleaned.length() == 9) || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid IRD Number: " + raw);
        }
        return cleaned;
    }
}
