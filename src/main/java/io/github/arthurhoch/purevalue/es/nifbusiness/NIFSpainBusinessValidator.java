package io.github.arthurhoch.purevalue.es.nifbusiness;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Spanish Business NIF.
 * Expects a format: 1 letter, 7 digits, and 1 control character (digit or letter A-J).
 * This simple validator checks the pattern only.
 */
public final class NIFSpainBusinessValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("^[A-Z]\\d{7}[0-9A-J]$");

    @Override
    public boolean isValid(String value) {
        if(value == null) return false;
        String cleaned = clean(value);
        return RAW_PATTERN.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\s", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if(cleaned == null || !isValid(cleaned)){
            throw new IllegalArgumentException("Invalid NIF Spain Business: " + raw);
        }
        return cleaned;
    }
}
