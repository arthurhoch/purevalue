package io.github.arthurhoch.purevalue.in.pincode;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Indian PIN Code.
 * Expects exactly 6 digits.
 */
public final class PinCodeIndiaValidator implements ValueValidator {
    private static final Pattern PATTERN = Pattern.compile("^\\d{6}$");

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
            throw new IllegalArgumentException("Invalid PinCodeIndia: " + raw);
        }
        return cleaned;
    }
}
