package io.github.arthurhoch.purevalue.uk.crn;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing UK Company Registration Number (CRN).
 */
public record CRNUK(String value) implements PureValue<String> {
    private static final CRNUKValidator validator = new CRNUKValidator();

    public CRNUK {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid CRN UK: " + value);
        }
    }

    @JsonCreator
    public static CRNUK fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static CRNUK of(String value) {
        return new CRNUK(value);
    }

    public static CRNUK ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new CRNUK(value);
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
