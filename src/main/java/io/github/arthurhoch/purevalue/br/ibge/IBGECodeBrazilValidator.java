package io.github.arthurhoch.purevalue.br.ibge;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for IBGE Code (Brazilian Municipality Code).
 * Expects exactly 7 digits.
 */
public final class IBGECodeBrazilValidator implements ValueValidator {
    private static final Pattern PATTERN = Pattern.compile("^\\d{7}$");

    @Override
    public boolean isValid(String value) {
        return value != null && PATTERN.matcher(value.trim()).matches();
    }

    public String clean(String value) {
        return (value == null) ? null : value.trim();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid IBGE Code Brazil: " + raw);
        }
        return cleaned;
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }
}
