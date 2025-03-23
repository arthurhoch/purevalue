package io.github.arthurhoch.purevalue.au.postal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an Australian Postcode.
 */
public record PostcodeAustralia(String value) implements PureValue<String> {
    private static final PostcodeAustraliaValidator validator = new PostcodeAustraliaValidator();

    public PostcodeAustralia {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Postcode Australia: " + value);
        }
    }

    @JsonCreator
    public static PostcodeAustralia fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static PostcodeAustralia of(String value) {
        return new PostcodeAustralia(value);
    }

    public static PostcodeAustralia ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new PostcodeAustralia(value);
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
