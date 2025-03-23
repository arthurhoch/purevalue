// File: SVNR.java
package io.github.arthurhoch.purevalue.at.svnr;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Austrian SVNR.
 */
public record SVNR(String value) implements PureValue<String> {
    private static final SVNRValidator validator = new SVNRValidator();

    public SVNR {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid SVNR: " + value);
        }
    }

    @JsonCreator
    public static SVNR fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static SVNR of(String value) {
        return new SVNR(value);
    }

    public static SVNR ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new SVNR(value);
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
