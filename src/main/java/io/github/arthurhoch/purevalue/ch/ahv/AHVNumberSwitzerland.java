// File: AHVNumberSwitzerland.java
package io.github.arthurhoch.purevalue.ch.ahv;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Swiss AHV Number.
 */
public record AHVNumberSwitzerland(String value) implements PureValue<String> {
    private static final AHVNumberSwitzerlandValidator validator = new AHVNumberSwitzerlandValidator();

    public AHVNumberSwitzerland {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid AHV Number Switzerland: " + value);
        }
    }

    @JsonCreator
    public static AHVNumberSwitzerland fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static AHVNumberSwitzerland of(String value) {
        return new AHVNumberSwitzerland(value);
    }

    public static AHVNumberSwitzerland ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new AHVNumberSwitzerland(value);
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
