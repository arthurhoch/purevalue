// File: RFCMexico.java
package io.github.arthurhoch.purevalue.mx.rfc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Mexican RFC.
 */
public record RFCMexico(String value) implements PureValue<String> {
    private static final RFCMexicoValidator validator = new RFCMexicoValidator();

    public RFCMexico {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid RFC: " + value);
        }
    }

    @JsonCreator
    public static RFCMexico fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static RFCMexico of(String value) {
        return new RFCMexico(value);
    }

    public static RFCMexico ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new RFCMexico(value);
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
