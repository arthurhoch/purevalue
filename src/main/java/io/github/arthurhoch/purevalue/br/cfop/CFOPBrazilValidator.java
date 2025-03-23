package io.github.arthurhoch.purevalue.br.cfop;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Brazilian CFOP (Código Fiscal de Operações e Prestações).
 * Expects exactly 4 digits.
 */
public final class CFOPBrazilValidator implements ValueValidator {
    private static final Pattern PATTERN = Pattern.compile("^\\d{4}$");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        return PATTERN.matcher(value.trim()).matches();
    }

    public String clean(String value) {
        return (value == null) ? null : value.trim();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid CFOP Brazil: " + raw);
        }
        return cleaned;
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }
}
