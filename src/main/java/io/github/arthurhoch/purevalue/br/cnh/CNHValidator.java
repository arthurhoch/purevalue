package io.github.arthurhoch.purevalue.br.cnh;

import io.github.arthurhoch.purevalue.core.ValueValidator;

import java.util.regex.Pattern;

public final class CNHValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{11}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;

        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        if (cleaned.matches("0{11}")) return false;

        String base = cleaned.substring(0, 9);
        int d1 = calculateDigit(base, 9);
        int d2 = calculateDigit(base + d1, 1);

        String expected = base + d1 + d2;
        return cleaned.equals(expected);
    }

    /**
     * Cálculo oficial dos dígitos verificadores da CNH:
     * Primeiro dígito usa pesos de 9 a 1.
     * Segundo dígito usa pesos de 1 a 9, incluindo o primeiro dígito no final da base.
     */
    private int calculateDigit(String base, int type) {
        int sum = 0;
        int weight = (type == 9) ? 9 : 1;

        for (int i = 0; i < 9; i++) {
            int digit = base.charAt(i) - '0';
            sum += digit * weight;
            weight += (type == 9) ? -1 : 1;
        }

        int result = sum % 11;
        if (result >= 10) result = 0;
        return result;
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(value).matches();
    }

    public String clean(String value) {
        return (value == null) ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 11) {
            throw new IllegalArgumentException("CNH inválida: deve conter 11 dígitos.");
        }
        return cleaned;
    }
}
