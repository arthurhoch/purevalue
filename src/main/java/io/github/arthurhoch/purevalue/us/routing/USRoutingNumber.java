// File: USRoutingNumber.java
package io.github.arthurhoch.purevalue.us.routing;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a US Routing Number.
 */
public record USRoutingNumber(String value) implements PureValue<String> {
    private static final USRoutingNumberValidator validator = new USRoutingNumberValidator();

    public USRoutingNumber {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid US Routing Number: " + value);
        }
    }

    @JsonCreator
    public static USRoutingNumber fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static USRoutingNumber of(String value) {
        return new USRoutingNumber(value);
    }

    public static USRoutingNumber ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new USRoutingNumber(value);
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

    @Override
    public String toString() {
        return value;
    }
}
