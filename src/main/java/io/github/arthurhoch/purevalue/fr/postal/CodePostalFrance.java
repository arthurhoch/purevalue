package io.github.arthurhoch.purevalue.fr.postal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a French postal code (Code Postal).
 */
public record CodePostalFrance(String value) implements PureValue<String> {
    private static final CodePostalFranceValidator validator = new CodePostalFranceValidator();

    public CodePostalFrance {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Code Postal France: " + value);
        }
    }

    @JsonCreator
    public static CodePostalFrance fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static CodePostalFrance of(String value) {
        return new CodePostalFrance(value);
    }

    public static CodePostalFrance ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new CodePostalFrance(value);
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
