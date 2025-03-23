package io.github.arthurhoch.purevalue.alarm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing an Alarm Code.
 */
public record AlarmCode(String value) implements PureValue<String> {
    private static final AlarmCodeValidator validator = new AlarmCodeValidator();

    public AlarmCode {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Alarm Code: " + value);
        }
    }

    @JsonCreator
    public static AlarmCode fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static AlarmCode of(String value) {
        return new AlarmCode(value);
    }

    public static AlarmCode ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new AlarmCode(value);
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
