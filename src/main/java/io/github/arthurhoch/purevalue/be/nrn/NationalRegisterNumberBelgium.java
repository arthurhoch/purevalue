// File: NationalRegisterNumberBelgium.java
package io.github.arthurhoch.purevalue.be.nrn;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Belgian National Register Number.
 */
public record NationalRegisterNumberBelgium(String value) implements PureValue<String> {
    private static final NationalRegisterNumberBelgiumValidator validator = new NationalRegisterNumberBelgiumValidator();

    public NationalRegisterNumberBelgium {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid National Register Number Belgium: " + value);
        }
    }

    @JsonCreator
    public static NationalRegisterNumberBelgium fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static NationalRegisterNumberBelgium of(String value) {
        return new NationalRegisterNumberBelgium(value);
    }

    public static NationalRegisterNumberBelgium ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new NationalRegisterNumberBelgium(value);
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
