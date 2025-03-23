package io.github.arthurhoch.purevalue.book;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an ISSN.
 */
public record ISSN(String value) implements PureValue<String> {
    private static final ISSNValidator validator = new ISSNValidator();

    public ISSN {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid ISSN: " + value);
        }
    }

    @JsonCreator
    public static ISSN fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static ISSN of(String value) {
        return new ISSN(value);
    }

    public static ISSN ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new ISSN(value);
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
