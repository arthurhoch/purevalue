package io.github.arthurhoch.purevalue.postbox;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a generic PO Box Number.
 */
public record POBoxNumber(String value) implements PureValue<String> {
    private static final POBoxNumberValidator validator = new POBoxNumberValidator();

    public POBoxNumber {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid PO Box Number: " + value);
        }
    }

    @JsonCreator
    public static POBoxNumber fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static POBoxNumber of(String value) {
        return new POBoxNumber(value);
    }

    public static POBoxNumber ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new POBoxNumber(value);
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
