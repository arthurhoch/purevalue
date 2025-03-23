package io.github.arthurhoch.purevalue.uk.sortcode;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a UK Sort Code.
 */
public record SortCodeUK(String value) implements PureValue<String> {
    private static final SortCodeUKValidator validator = new SortCodeUKValidator();

    public SortCodeUK {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Sort Code UK: " + value);
        }
    }

    @JsonCreator
    public static SortCodeUK fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static SortCodeUK of(String value) {
        return new SortCodeUK(value);
    }

    public static SortCodeUK ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new SortCodeUK(value);
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
