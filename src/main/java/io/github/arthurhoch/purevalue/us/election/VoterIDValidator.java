package io.github.arthurhoch.purevalue.us.election;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Voter ID.
 * This generic implementation accepts an alphanumeric string (after cleaning)
 * with a length between 5 and 20.
 */
public final class VoterIDValidator implements ValueValidator {
    private static final Pattern PATTERN = Pattern.compile("^[A-Z0-9]{5,20}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return PATTERN.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }

    public String clean(String value) {
        return (value == null) ? null : value.trim().replaceAll("\\s+", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Voter ID: " + raw);
        }
        return cleaned;
    }
}