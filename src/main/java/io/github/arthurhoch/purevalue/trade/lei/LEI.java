package io.github.arthurhoch.purevalue.trade.lei;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an LEI.
 */
public record LEI(String value) implements PureValue<String> {
    private static final LEIValidator validator = new LEIValidator();

    public LEI {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid LEI: " + value);
        }
    }

    @JsonCreator
    public static LEI fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static LEI of(String value) {
        return new LEI(value);
    }

    public static LEI ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new LEI(value);
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
