package io.github.arthurhoch.purevalue.trade.bl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a generic Bill of Lading Number.
 */
public record BillOfLadingNumber(String value) implements PureValue<String> {
    private static final BillOfLadingNumberValidator validator = new BillOfLadingNumberValidator();

    public BillOfLadingNumber {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Bill of Lading Number: " + value);
        }
    }

    @JsonCreator
    public static BillOfLadingNumber fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static BillOfLadingNumber of(String value) {
        return new BillOfLadingNumber(value);
    }

    public static BillOfLadingNumber ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new BillOfLadingNumber(value);
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
