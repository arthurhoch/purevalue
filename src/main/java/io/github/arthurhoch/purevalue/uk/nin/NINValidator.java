// File: NINValidator.java
package io.github.arthurhoch.purevalue.uk.nin;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Validator for UK National Insurance Number (NIN).
 * Expects two letters (excluding forbidden combinations), six digits, and one letter (A-D).
 */
public final class NINValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("^[A-CEGHJ-PR-TW-Z]{2}\\d{6}[A-D]$");
    private static final Set<String> FORBIDDEN_PREFIXES = new HashSet<>(Arrays.asList(
            "BG", "GB", "NK", "KN", "TN", "NT", "ZZ"
    ));

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        String prefix = cleaned.substring(0, 2);
        return !FORBIDDEN_PREFIXES.contains(prefix);
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\s", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid NIN: " + raw);
        }
        return cleaned;
    }
}
