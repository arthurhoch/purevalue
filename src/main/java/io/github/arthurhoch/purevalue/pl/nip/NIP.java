package io.github.arthurhoch.purevalue.pl.nip;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Polish NIP.
 */
public record NIP(String value) implements PureValue<String> {
    private static final NIPValidator validator = new NIPValidator();

    public NIP {
        value = clean(value);
        if(!isValid(value)){
            throw new IllegalArgumentException("Invalid NIP (Poland): " + value);
        }
    }

    @JsonCreator
    public static NIP fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static NIP of(String value) {
        return new NIP(value);
    }

    public static NIP ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new NIP(value);
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
