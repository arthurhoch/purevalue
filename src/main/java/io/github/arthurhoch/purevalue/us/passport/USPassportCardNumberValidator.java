// File: USPassportCardNumberValidator.java
package io.github.arthurhoch.purevalue.us.passport;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for US Passport Card Number.
 * Validates that the value consists of 9 alphanumeric characters.
 */
public final class USPassportCardNumberValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("[A-Z0-9]{9}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (cleaned.length() != 9) return false;
        return RAW_PATTERN.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        // For Passport Card Number, formatted form is identical to raw form.
        return value != null && RAW_PATTERN.matcher(value).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("[^A-Za-z0-9]", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 9) {
            throw new IllegalArgumentException("Invalid US Passport Card Number: Requires 9 alphanumeric characters after cleaning.");
        }
        return cleaned;
    }
}
