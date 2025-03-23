package io.github.arthurhoch.purevalue.internet.bt;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Bluetooth Address.
 * Accepts addresses with or without separators. After cleaning, must have exactly 12 hexadecimal characters.
 */
public final class BluetoothAddressValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("[0-9A-Fa-f]{12}");

    @Override
    public boolean isValid(String value) {
        if(value == null) return false;
        String cleaned = clean(value);
        return RAW_PATTERN.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("[:-]", "").replaceAll("\\s", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if(cleaned == null || cleaned.length() != 12 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Bluetooth Address: " + raw);
        }
        return cleaned;
    }
}
