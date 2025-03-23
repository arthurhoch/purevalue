package io.github.arthurhoch.purevalue.pt.nipc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Portuguese NIPC.
 */
public record NIPC(String value) implements PureValue<String> {
    private static final NIPCValidator validator = new NIPCValidator();

    public NIPC {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid NIPC: " + value);
        }
    }

    @JsonCreator
    public static NIPC fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static NIPC of(String value) {
        return new NIPC(value);
    }

    public static NIPC ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new NIPC(value);
    }

    public static boolean isValid(String value) {
        return validator.isValid(clean(value));
    }

    public static boolean isFormatted(String value) {
        return validator.isFormatted(value);
    }

    public static String clean(String value) {
        return validator.clean(value);
    }

    @Override
    public String format() {
        return validator.format(value);
    }
}
