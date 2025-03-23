package io.github.arthurhoch.purevalue.us.military;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Military ID.
 */
public record MilitaryID(String value) implements PureValue<String> {
    private static final MilitaryIDValidator validator = new MilitaryIDValidator();

    public MilitaryID {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Military ID: " + value);
        }
    }

    @JsonCreator
    public static MilitaryID fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static MilitaryID of(String value) {
        return new MilitaryID(value);
    }

    public static MilitaryID ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new MilitaryID(value);
    }

    public static boolean isValid(String value) {
        return validator.isValid(value);
    }

    public static String clean(String value) {
        return validator.clean(value);
    }

    @Override
    public String format() {
        return validator.format(value);
    }
}