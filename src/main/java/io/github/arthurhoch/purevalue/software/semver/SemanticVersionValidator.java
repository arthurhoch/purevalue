package io.github.arthurhoch.purevalue.software.semver;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Semantic Versioning (SemVer) according to SemVer 2.0.0.
 * Accepts versions in the form: MAJOR.MINOR.PATCH with optional pre-release and build metadata.
 */
public final class SemanticVersionValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile(
            "^(0|[1-9]\\d*)\\.(0|[1-9]\\d*)\\.(0|[1-9]\\d*)" +
                    "(?:-((?:0|[1-9A-Za-z-][0-9A-Za-z-]*)(?:\\.(?:0|[1-9A-Za-z-][0-9A-Za-z-]*))*))?" +
                    "(?:\\+([0-9A-Za-z-]+(?:\\.[0-9A-Za-z-]+)*))?$"
    );

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
        return value == null ? null : value.trim();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (!isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Semantic Version: " + raw);
        }
        return cleaned;
    }
}
