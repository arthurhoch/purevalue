// File: DNIArgentinaValidator.java
package io.github.arthurhoch.purevalue.ar.dni;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Argentine DNI.
 * Accepts 7 or 8 digit numbers and rejects all zeros.
 */
public final class DNIArgentinaValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{7,8}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        return !cleaned.matches("0+");
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !RAW_PATTERN.matcher(cleaned).matches() || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid DNI: " + raw);
        }
        return cleaned;
    }
}
