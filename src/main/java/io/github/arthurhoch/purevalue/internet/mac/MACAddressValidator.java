package io.github.arthurhoch.purevalue.internet.mac;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for MAC addresses.
 * Accepts addresses with or without separators. After cleaning, must have exactly 12 hexadecimal characters.
 */
public final class MACAddressValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("^[0-9A-F]{12}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return RAW_PATTERN.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        // Accept common MAC formats (colon, dash, or dot-separated)
        return value != null && (value.matches("([0-9A-Fa-f]{2}[:-]){5}[0-9A-Fa-f]{2}") ||
                value.matches("([0-9A-Fa-f]{4}\\.){2}[0-9A-Fa-f]{4}") ||
                value.replaceAll("[^0-9A-Fa-f]", "").length() == 12);
    }

    // Corrected: dash is placed at the beginning of the character class.
    public String clean(String value) {
        return (value == null) ? null : value.replaceAll("[-:\\.\\s]", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 12 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid MAC Address: " + raw);
        }
        return cleaned;
    }
}
