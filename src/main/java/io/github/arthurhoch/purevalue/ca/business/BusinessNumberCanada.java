package io.github.arthurhoch.purevalue.ca.business;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing the Canadian Business Number.
 */
public record BusinessNumberCanada(String value) implements PureValue<String> {
    private static final BusinessNumberCanadaValidator validator = new BusinessNumberCanadaValidator();

    public BusinessNumberCanada {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Business Number Canada: " + value);
        }
    }

    @JsonCreator
    public static BusinessNumberCanada fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static BusinessNumberCanada of(String value) {
        return new BusinessNumberCanada(value);
    }

    public static BusinessNumberCanada ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new BusinessNumberCanada(value);
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
