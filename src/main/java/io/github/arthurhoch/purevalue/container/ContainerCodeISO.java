package io.github.arthurhoch.purevalue.container;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an ISO 6346 Container Code.
 */
public record ContainerCodeISO(String value) implements PureValue<String> {
    private static final ContainerCodeISOValidator validator = new ContainerCodeISOValidator();

    public ContainerCodeISO {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Container Code ISO: " + value);
        }
    }

    @JsonCreator
    public static ContainerCodeISO fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static ContainerCodeISO of(String value) {
        return new ContainerCodeISO(value);
    }

    public static ContainerCodeISO ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new ContainerCodeISO(value);
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
