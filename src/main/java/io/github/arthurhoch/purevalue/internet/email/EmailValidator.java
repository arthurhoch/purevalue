package io.github.arthurhoch.purevalue.internet.email;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Email addresses.
 * This regex is adapted from RFC 5322 official standard,
 * adjusted for real-world usage (without full quoted-string support).
 */
public final class EmailValidator implements ValueValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            // Local-part + @ + domain (adapted from RFC 5322)
            "^(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*)@" +
                    "(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?\\.)+" +  // domain
                    "[a-zA-Z]{2,})$",                                             // TLD
            Pattern.CASE_INSENSITIVE
    );

    @Override
    public boolean isValid(String value) {
        if (value == null || value.length() > 320) return false;
        return EMAIL_PATTERN.matcher(value.trim()).matches();
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
            throw new IllegalArgumentException("Invalid Email: " + raw);
        }
        return cleaned;
    }
}
