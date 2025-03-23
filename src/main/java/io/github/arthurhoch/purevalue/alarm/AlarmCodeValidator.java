package io.github.arthurhoch.purevalue.alarm;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Alarm Code (Security system PIN).
 * Expects a numeric code between 4 and 6 digits.
 */
public final class AlarmCodeValidator implements ValueValidator {
    private static final Pattern PATTERN = Pattern.compile("^\\d{4,6}$");

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
            throw new IllegalArgumentException("Invalid Alarm Code: " + raw);
        }
        return cleaned;
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }
}
