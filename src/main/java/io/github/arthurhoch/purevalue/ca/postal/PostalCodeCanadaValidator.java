package io.github.arthurhoch.purevalue.ca.postal;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Canadian Postal Code.
 * Expects the format: LetterDigitLetter [optional space] DigitLetterDigit.
 */
public final class PostalCodeCanadaValidator implements ValueValidator {
    // Regex for Canadian postal code (ignoring some rare exceptions).
    private static final Pattern RAW_PATTERN = Pattern.compile(
            "^[ABCEGHJ-NPRSTVXY]\\d[ABCEGHJ-NPRSTV-Z]\\s?\\d[ABCEGHJ-NPRSTV-Z]\\d$",
            Pattern.CASE_INSENSITIVE
    );

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return RAW_PATTERN.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        return value != null && isValid(value);
    }

    public String clean(String value) {
        return (value == null) ? null : value.trim().toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Postal Code Canada: " + raw);
        }
        // Ensure format "A1A 1A1": if missing space, insert it.
        if (cleaned.length() == 6) {
            return cleaned.substring(0, 3) + " " + cleaned.substring(3);
        }
        return cleaned;
    }
}
