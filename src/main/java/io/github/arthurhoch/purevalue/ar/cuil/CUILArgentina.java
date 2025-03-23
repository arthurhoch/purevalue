// File: CUILArgentina.java
package io.github.arthurhoch.purevalue.ar.cuil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Argentine CUIL.
 */
public record CUILArgentina(String value) implements PureValue<String> {
    private static final CUILArgentinaValidator validator = new CUILArgentinaValidator();

    public CUILArgentina {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid CUIL: " + value);
        }
    }

    @JsonCreator
    public static CUILArgentina fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static CUILArgentina of(String value) {
        return new CUILArgentina(value);
    }

    public static CUILArgentina ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new CUILArgentina(value);
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
