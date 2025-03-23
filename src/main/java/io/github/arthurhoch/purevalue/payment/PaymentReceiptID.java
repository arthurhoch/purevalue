package io.github.arthurhoch.purevalue.payment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Payment Receipt ID.
 */
public record PaymentReceiptID(String value) implements PureValue<String> {
    private static final PaymentReceiptIDValidator validator = new PaymentReceiptIDValidator();

    public PaymentReceiptID {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Payment Receipt ID: " + value);
        }
    }

    @JsonCreator
    public static PaymentReceiptID fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static PaymentReceiptID of(String value) {
        return new PaymentReceiptID(value);
    }

    public static PaymentReceiptID ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new PaymentReceiptID(value);
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
