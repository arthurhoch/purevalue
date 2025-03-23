// File: EINValidator.java
package io.github.arthurhoch.purevalue.us.ein;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for US Employer Identification Number (EIN).
 * Validates both raw (9 digits) and formatted (XX-XXXXXXX) forms.
 */
public final class EINValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{9}");
    private static final Pattern FORMATTED_PATTERN = Pattern.compile("\\d{2}-\\d{7}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        // The first two digits cannot be "00"
        if (cleaned.startsWith("00")) return false;
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
        if (cleaned == null || cleaned.length() != 9) {
            throw new IllegalArgumentException("Invalid EIN: Requires 9 digits after cleaning.");
        }
        return cleaned.substring(0, 2) + "-" + cleaned.substring(2, 9);
    }
}
