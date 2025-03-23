package io.github.arthurhoch.purevalue.br.pispasep;

import io.github.arthurhoch.purevalue.core.ValueValidator;

import java.util.regex.Pattern;

public final class PISPASEPValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{11}");
    private static final Pattern FORMATTED_PATTERN = Pattern.compile("\\d{3}\\.\\d{5}\\.\\d{2}-\\d");
    private static final int[] WEIGHTS = {3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;

        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        if (cleaned.matches("0{11}")) return false;

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            int digit = cleaned.charAt(i) - '0';
            sum += digit * WEIGHTS[i];
        }

        int remainder = sum % 11;
        int checkDigit = (remainder < 2) ? 0 : 11 - remainder;

        return checkDigit == (cleaned.charAt(10) - '0');
    }

    public boolean isFormatted(String value) {
        return value != null && FORMATTED_PATTERN.matcher(value).matches();
    }

    public String clean(String value) {
        return (value == null) ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 11) {
            throw new IllegalArgumentException("Invalid PIS/PASEP: must contain exactly 11 digits after cleaning.");
        }
        return cleaned.substring(0, 3) + "."
                + cleaned.substring(3, 8) + "."
                + cleaned.substring(8, 10) + "-"
                + cleaned.substring(10);
    }
}
