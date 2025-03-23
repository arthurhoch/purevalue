package io.github.arthurhoch.purevalue.internet.domain;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for domain registration names.
 * Accepts typical domain names (e.g., "example.com", "sub.domain.co.uk").
 * This is a simplified regex covering common cases.
 */
public final class DomainRegistrationValidator implements ValueValidator {
    private static final Pattern DOMAIN_PATTERN = Pattern.compile(
            "^(?=.{1,253}$)(?!-)[A-Z0-9-]+(\\.[A-Z0-9-]+)*\\.[A-Z]{2,}$",
            Pattern.CASE_INSENSITIVE
    );

    @Override
    public boolean isValid(String value) {
        if(value == null) return false;
        return DOMAIN_PATTERN.matcher(value.trim()).matches();
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }

    public String clean(String value) {
        return (value == null) ? null : value.trim().toLowerCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid Domain Registration: " + raw);
        }
        return cleaned;
    }
}
