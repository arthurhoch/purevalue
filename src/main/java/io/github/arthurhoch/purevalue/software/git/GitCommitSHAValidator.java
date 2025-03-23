package io.github.arthurhoch.purevalue.software.git;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Git Commit SHA.
 * Accepts either a 40-character SHA-1 or a 64-character SHA-256, both hexadecimal.
 */
public final class GitCommitSHAValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("^(?:[0-9A-Fa-f]{40}|[0-9A-Fa-f]{64})$");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return RAW_PATTERN.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\s", "").toLowerCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (!isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Git Commit SHA: " + raw);
        }
        return cleaned;
    }
}
