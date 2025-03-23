package io.github.arthurhoch.purevalue.jp.postcode;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Japanese postal codes.
 * Accepts either a raw version (exactly 7 digits) or a formatted version ("NNN-NNNN").
 */
public final class PostcodeJapanValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("^\\d{7}$");
    private static final Pattern FORMATTED_PATTERN = Pattern.compile("^\\d{3}-\\d{4}$");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String trimmed = value.trim();
        // If the input contains a hyphen, it must match the formatted pattern.
        if (trimmed.contains("-")) {
            return FORMATTED_PATTERN.matcher(trimmed).matches();
        }
        // Otherwise, it must be exactly 7 digits.
        return RAW_PATTERN.matcher(trimmed).matches();
    }

    public boolean isFormatted(String value) {
        return value != null && FORMATTED_PATTERN.matcher(value.trim()).matches();
    }

    public String clean(String value) {
        return (value == null) ? null : value.replaceAll("\\D", "");
    }

    /**
     * If input is raw (7 digits), returns formatted as "NNN-NNNN".
     * If input is already formatted, returns it.
     */
    public String format(String raw) {
        if (raw == null) throw new IllegalArgumentException("Null input");
        String trimmed = raw.trim();
        if (trimmed.contains("-")) {
            if (!FORMATTED_PATTERN.matcher(trimmed).matches()) {
                throw new IllegalArgumentException("Invalid formatted postal code: " + raw);
            }
            return trimmed;
        } else {
            // Must be 7 digits.
            String cleaned = clean(trimmed);
            if (!RAW_PATTERN.matcher(cleaned).matches()) {
                throw new IllegalArgumentException("Invalid postal code: " + raw);
            }
            return cleaned.substring(0, 3) + "-" + cleaned.substring(3);
        }
    }
}
