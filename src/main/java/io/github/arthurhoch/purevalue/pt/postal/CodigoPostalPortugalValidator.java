package io.github.arthurhoch.purevalue.pt.postal;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Portuguese Postal Code (Código Postal).
 * Accepts either:
 * - A raw version: exactly 7 digits, or
 * - A formatted version: exactly 4 digits, a hyphen, and 3 digits ("NNNN-NNN").
 */
public final class CodigoPostalPortugalValidator implements ValueValidator {
    private static final Pattern FORMATTED_PATTERN = Pattern.compile("^\\d{4}-\\d{3}$");
    private static final Pattern RAW_PATTERN = Pattern.compile("^\\d{7}$");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        // If the original input contains any non-digits, it must match the formatted pattern.
        if (!value.matches("^\\d+$")) {
            if (!value.matches("^\\d{4}-\\d{3}$")) return false;
        }
        // In either case, after cleaning, the value must be exactly 7 digits.
        String cleaned = clean(value);
        return RAW_PATTERN.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        // If the input contains non-digits, it must match the formatted pattern.
        return value != null && value.matches("^\\d{4}-\\d{3}$");
    }

    public String clean(String value) {
        return (value == null) ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        // If raw input contains non-digit characters, enforce formatted pattern.
        if (raw != null && !raw.matches("^\\d+$")) {
            if (!raw.matches("^\\d{4}-\\d{3}$")) {
                throw new IllegalArgumentException("Invalid Codigo Postal Portugal (formatted): " + raw);
            }
            return raw.trim();
        }
        // If input is raw digits, then ensure it has exactly 7 digits.
        String cleaned = clean(raw);
        if (cleaned == null || !RAW_PATTERN.matcher(cleaned).matches()) {
            throw new IllegalArgumentException("Invalid Codigo Postal Portugal: " + raw);
        }
        // Format as "NNNN-NNN"
        return cleaned.substring(0, 4) + "-" + cleaned.substring(4);
    }
}
