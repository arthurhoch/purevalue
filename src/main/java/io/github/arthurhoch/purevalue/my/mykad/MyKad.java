// File: MyKad.java
package io.github.arthurhoch.purevalue.my.mykad;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Malaysian MyKad.
 */
public record MyKad(String value) implements PureValue<String> {
    private static final MyKadValidator validator = new MyKadValidator();

    public MyKad {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid MyKad: " + value);
        }
    }

    @JsonCreator
    public static MyKad fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static MyKad of(String value) {
        return new MyKad(value);
    }

    public static MyKad ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new MyKad(value);
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
