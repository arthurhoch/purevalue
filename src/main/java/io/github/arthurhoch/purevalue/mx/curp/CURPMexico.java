// File: CURPMexico.java
package io.github.arthurhoch.purevalue.mx.curp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Mexican CURP.
 */
public record CURPMexico(String value) implements PureValue<String> {
    private static final CURPMexicoValidator validator = new CURPMexicoValidator();

    public CURPMexico {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid CURP: " + value);
        }
    }

    @JsonCreator
    public static CURPMexico fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static CURPMexico of(String value) {
        return new CURPMexico(value);
    }

    public static CURPMexico ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new CURPMexico(value);
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
