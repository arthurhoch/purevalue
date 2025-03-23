// File: ChinaIDCard.java
package io.github.arthurhoch.purevalue.cn.idcard;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Chinese ID Card.
 */
public record ChinaIDCard(String value) implements PureValue<String> {
    private static final ChinaIDCardValidator validator = new ChinaIDCardValidator();

    public ChinaIDCard {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid ChinaIDCard: " + value);
        }
    }

    @JsonCreator
    public static ChinaIDCard fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static ChinaIDCard of(String value) {
        return new ChinaIDCard(value);
    }

    public static ChinaIDCard ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new ChinaIDCard(value);
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
