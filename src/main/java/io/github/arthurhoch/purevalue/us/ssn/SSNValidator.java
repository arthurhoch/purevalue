// File: SSNValidator.java
package io.github.arthurhoch.purevalue.us.ssn;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for US Social Security Number (SSN).
 * Validates both raw (9 digits) and formatted (XXX-XX-XXXX) forms.
 */
public final class SSNValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{9}");
    private static final Pattern FORMATTED_PATTERN = Pattern.compile("\\d{3}-\\d{2}-\\d{4}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;

        // Extract area, group, and serial numbers.
        String area = cleaned.substring(0, 3);
        String group = cleaned.substring(3, 5);
        String serial = cleaned.substring(5, 9);
        int areaNum = Integer.parseInt(area);
        int groupNum = Integer.parseInt(group);
        int serialNum = Integer.parseInt(serial);

        // Area number must be between 001 and 899, excluding 666.
        if (areaNum < 1 || areaNum > 899 || area.equals("666")) return false;
        // Group number must be between 01 and 99.
        if (groupNum < 1 || groupNum > 99) return false;
        // Serial number must be between 0001 and 9999.
        if (serialNum < 1 || serialNum > 9999) return false;

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
            throw new IllegalArgumentException("Invalid SSN: Requires 9 digits after cleaning.");
        }
        return cleaned.substring(0, 3) + "-" +
                cleaned.substring(3, 5) + "-" +
                cleaned.substring(5, 9);
    }
}
