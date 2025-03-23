package io.github.arthurhoch.purevalue.finance.credit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Credit Card Number.
 */
public record CreditCardNumber(String value) implements PureValue<String> {
    private static final CreditCardNumberValidator validator = new CreditCardNumberValidator();

    public CreditCardNumber {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Credit Card Number: " + value);
        }
    }

    @JsonCreator
    public static CreditCardNumber fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static CreditCardNumber of(String value) {
        return new CreditCardNumber(value);
    }

    public static CreditCardNumber ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new CreditCardNumber(value);
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
