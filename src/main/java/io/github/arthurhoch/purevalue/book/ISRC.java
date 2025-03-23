package io.github.arthurhoch.purevalue.book;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an ISRC.
 */
public record ISRC(String value) implements PureValue<String> {
    private static final ISRCValidator validator = new ISRCValidator();

    public ISRC {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid ISRC: " + value);
        }
    }

    @JsonCreator
    public static ISRC fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static ISRC of(String value) {
        return new ISRC(value);
    }

    public static ISRC ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new ISRC(value);
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
