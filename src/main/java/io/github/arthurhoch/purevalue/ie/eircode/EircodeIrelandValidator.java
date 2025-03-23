package io.github.arthurhoch.purevalue.ie.eircode;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Irish Eircode.
 * Accepts either a 7-character string without space or a formatted version "NNN AAAA".
 */
public final class EircodeIrelandValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("^[A-Z0-9]{7}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern FORMATTED_PATTERN = Pattern.compile("^[A-Z0-9]{3}\\s?[A-Z0-9]{4}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return RAW_PATTERN.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        return value != null && FORMATTED_PATTERN.matcher(value.trim()).matches();
    }

    public String clean(String value) {
        if (value == null) return null;
        return value.replaceAll("\\s", "").toUpperCase();
    }

    public String format(String raw) {
        if (raw == null) throw new IllegalArgumentException("Null input");
        String trimmed = raw.trim();
        if (trimmed.contains(" ")) {
            if (!isFormatted(trimmed)) {
                throw new IllegalArgumentException("Invalid Eircode Ireland format: " + raw);
            }
            return trimmed.toUpperCase();
        } else {
            if (!RAW_PATTERN.matcher(trimmed).matches()) {
                throw new IllegalArgumentException("Invalid Eircode Ireland: " + raw);
            }
            String cleaned = clean(trimmed);
            return cleaned.substring(0, 3) + " " + cleaned.substring(3);
        }
    }
}
