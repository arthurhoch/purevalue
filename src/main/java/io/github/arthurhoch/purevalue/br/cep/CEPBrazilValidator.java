package io.github.arthurhoch.purevalue.br.cep;

import io.github.arthurhoch.purevalue.core.ValueValidator;

import java.util.regex.Pattern;

public class CEPBrazilValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("^\\d{8}$");
    private static final Pattern FORMATTED_PATTERN = Pattern.compile("^\\d{5}-\\d{3}$");
    private static final String ALL_ZEROS = "0{8}";

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);

        // Verifica formato, dígitos e bloqueia sequências de zeros
        return RAW_PATTERN.matcher(cleaned).matches() && !cleaned.matches(ALL_ZEROS);
    }

    public boolean isFormatted(String value) {
        return value != null && FORMATTED_PATTERN.matcher(value).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String value) {
        String cleaned = clean(value);
        if (cleaned == null || cleaned.length() != 8) {
            throw new IllegalArgumentException("CEP inválido: é necessário conter exatamente 8 dígitos após limpeza.");
        }
        return cleaned.substring(0, 5) + "-" + cleaned.substring(5);
    }
}
