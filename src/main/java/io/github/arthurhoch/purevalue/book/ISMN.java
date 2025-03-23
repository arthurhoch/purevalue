package io.github.arthurhoch.purevalue.book;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an ISMN.
 */
public record ISMN(String value) implements PureValue<String> {
    private static final ISMNValidator validator = new ISMNValidator();

    public ISMN {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid ISMN: " + value);
        }
    }

    @JsonCreator
    public static ISMN fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static ISMN of(String value) {
        return new ISMN(value);
    }

    public static ISMN ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new ISMN(value);
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
