package io.github.arthurhoch.purevalue.gen.passport;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Passport Number.
 * This generic implementation accepts an alphanumeric string (letters and digits)
 * with a length between 6 and 9 characters.
 */
public final class PassportNumberValidator implements ValueValidator {
    private static final Pattern PATTERN = Pattern.compile("^[A-Z0-9]{6,9}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return PATTERN.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }

    public String clean(String value) {
        return (value == null) ? null : value.trim().toUpperCase().replaceAll("\\s+", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Passport Number: " + raw);
        }
        return cleaned;
    }
}
