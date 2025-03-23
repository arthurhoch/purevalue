// File: LicensePlateValidator.java
package io.github.arthurhoch.purevalue.vehicle;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for a generic vehicle License Plate.
 * Accepts license plates with alphanumeric characters (2 to 10 characters).
 */
public final class LicensePlateValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("[A-Z0-9]{2,10}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return RAW_PATTERN.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(value.replaceAll("[\\s-]", "").toUpperCase()).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("[^A-Za-z0-9]", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !RAW_PATTERN.matcher(cleaned).matches()) {
            throw new IllegalArgumentException("Invalid License Plate: Must be 2 to 10 alphanumeric characters after cleaning.");
        }
        return cleaned;
    }
}
