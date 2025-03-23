package io.github.arthurhoch.purevalue.br.boleto;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Brazilian Boleto.
 * Accepts either 47 or 48 digit strings.
 */
public final class BoletoBrazilValidator implements ValueValidator {
    private static final Pattern PATTERN = Pattern.compile("^\\d{47,48}$");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return PATTERN.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        // Boleto numbers are usually provided as a continuous digit string.
        return isValid(value);
    }

    public String clean(String value) {
        return (value == null) ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Boleto Brazil: " + raw);
        }
        return cleaned;
    }
}
