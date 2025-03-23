package io.github.arthurhoch.purevalue.software.semver;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Semantic Version.
 */
public record SemanticVersion(String value) implements PureValue<String> {
    private static final SemanticVersionValidator validator = new SemanticVersionValidator();

    public SemanticVersion {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Semantic Version: " + value);
        }
    }

    @JsonCreator
    public static SemanticVersion fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static SemanticVersion of(String value) {
        return new SemanticVersion(value);
    }

    public static SemanticVersion ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new SemanticVersion(value);
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
