package io.github.arthurhoch.purevalue.internet.cidr;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for CIDR Block (IPv4).
 * Expects an IPv4 address followed by a slash and a prefix length (0-32).
 */
public final class CIDRBlockValidator implements ValueValidator {
    // IPv4 CIDR regex pattern
    private static final Pattern PATTERN = Pattern.compile(
            "^(?:(?:25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d\\d?)/(?:[0-9]|[12]\\d|3[0-2])$"
    );

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
            throw new IllegalArgumentException("Invalid CIDR Block: " + raw);
        }
        return cleaned;
    }
}
