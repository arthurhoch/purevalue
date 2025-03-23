package io.github.arthurhoch.purevalue.nz.postal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a New Zealand Postcode.
 */
public record PostcodeNZ(String value) implements PureValue<String> {
    private static final PostcodeNZValidator validator = new PostcodeNZValidator();

    public PostcodeNZ {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid PostcodeNZ: " + value);
        }
    }

    @JsonCreator
    public static PostcodeNZ fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static PostcodeNZ of(String value) {
        return new PostcodeNZ(value);
    }

    public static PostcodeNZ ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new PostcodeNZ(value);
    }

    public static boolean isValid(String value) {
        return validator.isValid(value);
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
