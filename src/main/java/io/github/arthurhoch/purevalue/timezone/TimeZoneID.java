package io.github.arthurhoch.purevalue.timezone;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an IANA Time Zone ID.
 */
public record TimeZoneID(String value) implements PureValue<String> {
    private static final TimeZoneIDValidator validator = new TimeZoneIDValidator();

    public TimeZoneID {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid TimeZone ID: " + value);
        }
    }

    @JsonCreator
    public static TimeZoneID fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static TimeZoneID of(String value) {
        return new TimeZoneID(value);
    }

    public static TimeZoneID ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new TimeZoneID(value);
    }

    public static boolean isValid(String value) {
        return validator.isValid(clean(value));
    }

    public static String clean(String value) {
        return validator.clean(value);
    }

    @Override
    public String format() {
        return validator.format(value);
    }
}
