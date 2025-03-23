// File: CUITArgentina.java
package io.github.arthurhoch.purevalue.ar.cuit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Argentine CUIT.
 */
public record CUITArgentina(String value) implements PureValue<String> {
    private static final CUITArgentinaValidator validator = new CUITArgentinaValidator();

    public CUITArgentina {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid CUIT: " + value);
        }
    }

    @JsonCreator
    public static CUITArgentina fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static CUITArgentina of(String value) {
        return new CUITArgentina(value);
    }

    public static CUITArgentina ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new CUITArgentina(value);
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
