// File: USDOTNumberValidator.java
package io.github.arthurhoch.purevalue.us.vehicle;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for US USDOT Number used for commercial vehicles.
 * Accepts 6 or 7 digit numbers.
 */
public final class USDOTNumberValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{6,7}");
    private static final Pattern FORMATTED_PATTERN = RAW_PATTERN;

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        if (cleaned.matches("0+")) return false;
        return true;
    }

    public boolean isFormatted(String value) {
        return value != null && FORMATTED_PATTERN.matcher(value).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !RAW_PATTERN.matcher(cleaned).matches()) {
            throw new IllegalArgumentException("Invalid USDOT Number: Must be 6 or 7 digits after cleaning.");
        }
        return cleaned;
    }
}
