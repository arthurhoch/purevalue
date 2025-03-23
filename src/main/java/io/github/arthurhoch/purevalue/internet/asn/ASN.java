package io.github.arthurhoch.purevalue.internet.asn;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an Autonomous System Number (ASN).
 */
public record ASN(String value) implements PureValue<String> {
    private static final ASNValidator validator = new ASNValidator();

    public ASN {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid ASN: " + value);
        }
    }

    @JsonCreator
    public static ASN fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static ASN of(String value) {
        return new ASN(value);
    }

    public static ASN ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new ASN(value);
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
