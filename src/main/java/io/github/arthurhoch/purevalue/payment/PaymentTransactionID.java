package io.github.arthurhoch.purevalue.payment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Payment Transaction ID.
 */
public record PaymentTransactionID(String value) implements PureValue<String> {
    private static final PaymentTransactionIDValidator validator = new PaymentTransactionIDValidator();

    public PaymentTransactionID {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Payment Transaction ID: " + value);
        }
    }

    @JsonCreator
    public static PaymentTransactionID fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static PaymentTransactionID of(String value) {
        return new PaymentTransactionID(value);
    }

    public static PaymentTransactionID ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new PaymentTransactionID(value);
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
