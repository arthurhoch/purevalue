package io.github.arthurhoch.purevalue.internet.url;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Validator for URL.
 * Uses java.net.URL to check validity.
 */
public final class URLValueValidator implements ValueValidator {

    @Override
    public boolean isValid(String value) {
        if(value == null) return false;
        try {
            new URL(value.trim());
            return true;
        } catch (MalformedURLException ex) {
            return false;
        }
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }

    public String clean(String value) {
        return (value == null) ? null : value.trim();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid URL: " + raw);
        }
        return cleaned;
    }
}
