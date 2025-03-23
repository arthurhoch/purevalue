// File: IqamaNumber.java
package io.github.arthurhoch.purevalue.sa.iqama;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Saudi Iqama Number.
 */
public record IqamaNumber(String value) implements PureValue<String> {
    private static final IqamaNumberValidator validator = new IqamaNumberValidator();

    public IqamaNumber {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Iqama Number: " + value);
        }
    }

    @JsonCreator
    public static IqamaNumber fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static IqamaNumber of(String value) {
        return new IqamaNumber(value);
    }

    public static IqamaNumber ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new IqamaNumber(value);
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
