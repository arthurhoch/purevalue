// File: RFCMexicoValidator.java
package io.github.arthurhoch.purevalue.mx.rfc;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Mexican RFC (Registro Federal de Contribuyentes).
 * Accepts 12 or 13 characters:
 * - For companies: 3 letters, 6 digits (date), 3 alphanumeric.
 * - For individuals: 4 letters, 6 digits (date), 3 alphanumeric.
 */
public final class RFCMexicoValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("^(?:[A-ZÑ&]{3}\\d{6}[A-Z0-9]{3}|[A-ZÑ&]{4}\\d{6}[A-Z0-9]{3})$");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
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
        if (!isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid RFC: " + raw);
        }
        return cleaned;
    }
}
