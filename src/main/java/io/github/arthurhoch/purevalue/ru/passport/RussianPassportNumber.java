// File: RussianPassportNumber.java
package io.github.arthurhoch.purevalue.ru.passport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Russian Passport Number.
 */
public record RussianPassportNumber(String value) implements PureValue<String> {
    private static final RussianPassportNumberValidator validator = new RussianPassportNumberValidator();

    public RussianPassportNumber {
        value = clean(value);
        if(!isValid(value)) {
            throw new IllegalArgumentException("Invalid Russian Passport Number: " + value);
        }
    }

    @JsonCreator
    public static RussianPassportNumber fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static RussianPassportNumber of(String value) {
        return new RussianPassportNumber(value);
    }

    public static RussianPassportNumber ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new RussianPassportNumber(value);
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
