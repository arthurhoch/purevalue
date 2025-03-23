package io.github.arthurhoch.purevalue.internet.cidr;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a CIDR Block.
 */
public record CIDRBlock(String value) implements PureValue<String> {
    private static final CIDRBlockValidator validator = new CIDRBlockValidator();

    public CIDRBlock {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid CIDR Block: " + value);
        }
    }

    @JsonCreator
    public static CIDRBlock fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static CIDRBlock of(String value) {
        return new CIDRBlock(value);
    }

    public static CIDRBlock ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new CIDRBlock(value);
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
