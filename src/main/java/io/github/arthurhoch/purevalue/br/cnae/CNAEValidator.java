package io.github.arthurhoch.purevalue.br.cnae;

import io.github.arthurhoch.purevalue.core.ValueValidator;

import java.util.regex.Pattern;

public class CNAEValidator implements ValueValidator {

    // Aceita apenas 7 dígitos consecutivos para o CNAE "cru".
    private static final Pattern RAW_PATTERN = Pattern.compile("^\\d{7}$");

    // Aceita o formato "XX.XX-X-XX" (7 dígitos + separadores).
    private static final Pattern FORMATTED_PATTERN = Pattern.compile("^\\d{2}\\.\\d{2}-\\d-\\d{2}$");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        // Verifica se há exatamente 7 dígitos e se corresponde ao padrão cru.
        return cleaned.length() == 7 && RAW_PATTERN.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        return value != null && FORMATTED_PATTERN.matcher(value).matches();
    }

    public String clean(String value) {
        // Remove tudo que não for dígito.
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        // Valida se, após limpeza, temos 7 dígitos.
        if (cleaned == null || cleaned.length() != 7) {
            throw new IllegalArgumentException("CNAE inválido: é necessário conter exatamente 7 dígitos após limpeza.");
        }
        // Aplica o formato "XX.XX-X-XX" aos 7 dígitos.
        return cleaned.substring(0, 2)
                + "."
                + cleaned.substring(2, 4)
                + "-"
                + cleaned.substring(4, 5)
                + "-"
                + cleaned.substring(5, 7);
    }
}
