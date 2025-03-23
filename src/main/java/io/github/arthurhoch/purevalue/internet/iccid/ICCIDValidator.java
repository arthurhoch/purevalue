package io.github.arthurhoch.purevalue.internet.iccid;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for ICCID (Integrated Circuit Card Identifier).
 * Typically expects 19 or 20 digits.
 */
public final class ICCIDValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{19,20}");

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
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if(cleaned == null || !(cleaned.length() == 19 || cleaned.length() == 20) || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid ICCID: " + raw);
        }
        return cleaned;
    }
}
