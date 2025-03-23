package io.github.arthurhoch.purevalue.ca.business;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Canadian Business Number.
 * Expects exactly 9 digits.
 */
public final class BusinessNumberCanadaValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{9}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return RAW_PATTERN.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 9 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Business Number Canada: " + raw);
        }
        return cleaned;
    }
}
