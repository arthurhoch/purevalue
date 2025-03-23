package io.github.arthurhoch.purevalue.internet.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Domain Registration name.
 */
public record DomainRegistration(String value) implements PureValue<String> {
    private static final DomainRegistrationValidator validator = new DomainRegistrationValidator();

    public DomainRegistration {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Domain Registration: " + value);
        }
    }

    @JsonCreator
    public static DomainRegistration fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static DomainRegistration of(String value) {
        return new DomainRegistration(value);
    }

    public static DomainRegistration ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new DomainRegistration(value);
    }

    public static boolean isValid(String value) {
        return validator.isValid(value);
    }

    public static String clean(String value) {
        return validator.clean(value);
    }

    @Override
    public String format() {
        return validator.format(value);
    }
}
