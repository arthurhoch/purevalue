package io.github.arthurhoch.purevalue.timezone;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.time.ZoneId;
import java.util.Set;

/**
 * Validator for IANA Time Zone IDs.
 * Expects a valid time zone ID such as "America/Sao_Paulo".
 */
public final class TimeZoneIDValidator implements ValueValidator {
    private static final Set<String> VALID_ZONES = ZoneId.getAvailableZoneIds();

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String trimmed = value.trim();
        return VALID_ZONES.contains(trimmed);
    }

    public String clean(String value) {
        return (value == null) ? null : value.trim();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid TimeZone ID: " + raw);
        }
        return cleaned;
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }
}
