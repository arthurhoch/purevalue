// File: EuropeanLicensePlateValidator.java
package io.github.arthurhoch.purevalue.eu.licenseplate;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for European License Plate.
 * Accepts a generic format with alphanumeric characters (5 to 12 characters after cleaning).
 */
public final class EuropeanLicensePlateValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("[A-Z0-9]{5,12}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return RAW_PATTERN.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        return value != null && isValid(clean(value));
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("[^A-Za-z0-9]", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !RAW_PATTERN.matcher(cleaned).matches()) {
            throw new IllegalArgumentException("Invalid European License Plate: Must be 5 to 12 alphanumeric characters after cleaning.");
        }
        return cleaned;
    }
}
