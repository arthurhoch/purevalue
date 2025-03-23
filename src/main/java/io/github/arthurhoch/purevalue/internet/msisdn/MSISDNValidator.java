package io.github.arthurhoch.purevalue.internet.msisdn;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for MSISDN.
 * Expects a string in E.164 format: a leading '+' followed by 2 to 15 digits.
 */
public final class MSISDNValidator implements ValueValidator {
    private static final Pattern PATTERN = Pattern.compile("^\\+[1-9]\\d{1,14}$");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        return PATTERN.matcher(value.trim()).matches();
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }

    public String clean(String value) {
        return (value == null) ? null : value.trim();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid MSISDN: " + raw);
        }
        return cleaned;
    }
}
