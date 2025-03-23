package io.github.arthurhoch.purevalue.br.titulo;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

public final class TituloEleitorValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{12}");
    private static final Pattern FORMATTED_PATTERN = Pattern.compile("\\d{4}\\s\\d{4}\\s\\d{4}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        if (cleaned.matches("0{12}")) return false;

        // Calcula o primeiro dígito verificador usando os 8 primeiros dígitos com pesos decrescentes de 9 a 2
        int d1 = calculateFirstDigit(cleaned);

        // Valida o código da UF (posição 8 e 9)
        int uf = Integer.parseInt(cleaned.substring(8, 10));
        if (uf < 1 || uf > 28) return false;

        // Calcula o segundo dígito verificador utilizando a UF e o d1 calculado
        int d2 = calculateSecondDigit(cleaned, d1);

        return d1 == Character.getNumericValue(cleaned.charAt(10)) &&
                d2 == Character.getNumericValue(cleaned.charAt(11));
    }

    // Calcula o primeiro dígito usando pesos: 9, 8, 7, 6, 5, 4, 3, 2
    private static int calculateFirstDigit(String value) {
        int sum = 0;
        int[] weights = {9, 8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 8; i++) {
            int digit = Character.getNumericValue(value.charAt(i));
            sum += digit * weights[i];
        }
        int remainder = sum % 11;
        return (remainder < 2) ? 0 : 11 - remainder;
    }

    // Calcula o segundo dígito usando os dígitos da UF e o d1, com pesos: 7, 6 e 5
    private static int calculateSecondDigit(String value, int d1) {
        int sum = 0;
        int[] weights = {7, 6, 5};
        sum += Character.getNumericValue(value.charAt(8)) * weights[0];
        sum += Character.getNumericValue(value.charAt(9)) * weights[1];
        sum += d1 * weights[2];
        int remainder = sum % 11;
        return (remainder < 2) ? 0 : 11 - remainder;
    }

    public boolean isFormatted(String value) {
        return value != null && FORMATTED_PATTERN.matcher(value).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 12) {
            throw new IllegalArgumentException("Invalid Título de Eleitor: Needs 12 digits after cleaning.");
        }
        return cleaned.substring(0, 4) + " " +
                cleaned.substring(4, 8) + " " +
                cleaned.substring(8, 12);
    }
}
