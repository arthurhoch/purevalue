package io.github.arthurhoch.purevalue.ca.transit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Canadian Transit Number.
 */
public record TransitNumberCanada(String value) implements PureValue<String> {
    private static final TransitNumberCanadaValidator validator = new TransitNumberCanadaValidator();

    public TransitNumberCanada {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Transit Number Canada: " + value);
        }
    }

    @JsonCreator
    public static TransitNumberCanada fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static TransitNumberCanada of(String value) {
        return new TransitNumberCanada(value);
    }

    public static TransitNumberCanada ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new TransitNumberCanada(value);
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
