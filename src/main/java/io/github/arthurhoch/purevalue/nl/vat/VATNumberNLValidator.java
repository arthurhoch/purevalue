package io.github.arthurhoch.purevalue.nl.vat;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Dutch VAT Number.
 * Expects a format: NL followed by 9 digits, then the letter 'B' and 2 digits.
 */
public final class VATNumberNLValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("^NL\\d{9}B\\d{2}$", Pattern.CASE_INSENSITIVE);

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
        return (value == null) ? null : value.replaceAll("\\s", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if(cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid VAT Number NL: " + raw);
        }
        return cleaned;
    }
}
