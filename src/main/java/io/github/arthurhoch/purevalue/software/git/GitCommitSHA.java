package io.github.arthurhoch.purevalue.software.git;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Git Commit SHA.
 */
public record GitCommitSHA(String value) implements PureValue<String> {
    private static final GitCommitSHAValidator validator = new GitCommitSHAValidator();

    public GitCommitSHA {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Git Commit SHA: " + value);
        }
    }

    @JsonCreator
    public static GitCommitSHA fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static GitCommitSHA of(String value) {
        return new GitCommitSHA(value);
    }

    public static GitCommitSHA ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new GitCommitSHA(value);
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
