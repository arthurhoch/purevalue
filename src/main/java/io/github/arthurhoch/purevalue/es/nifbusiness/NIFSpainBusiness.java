package io.github.arthurhoch.purevalue.es.nifbusiness;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Spanish Business NIF.
 */
public record NIFSpainBusiness(String value) implements PureValue<String> {
    private static final NIFSpainBusinessValidator validator = new NIFSpainBusinessValidator();

    public NIFSpainBusiness {
        value = clean(value);
        if(!isValid(value)){
            throw new IllegalArgumentException("Invalid NIF Spain Business: " + value);
        }
    }

    @JsonCreator
    public static NIFSpainBusiness fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static NIFSpainBusiness of(String value) {
        return new NIFSpainBusiness(value);
    }

    public static NIFSpainBusiness ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new NIFSpainBusiness(value);
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
