package io.github.arthurhoch.purevalue.cn.unionpay;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a UnionPay Card Number.
 */
public record UnionPayCardNumber(String value) implements PureValue<String> {
    private static final UnionPayCardNumberValidator validator = new UnionPayCardNumberValidator();

    public UnionPayCardNumber {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid UnionPay Card Number: " + value);
        }
    }

    @JsonCreator
    public static UnionPayCardNumber fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static UnionPayCardNumber of(String value) {
        return new UnionPayCardNumber(value);
    }

    public static UnionPayCardNumber ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new UnionPayCardNumber(value);
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
