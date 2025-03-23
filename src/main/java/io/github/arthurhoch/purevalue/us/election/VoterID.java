package io.github.arthurhoch.purevalue.us.election;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Voter ID.
 */
public record VoterID(String value) implements PureValue<String> {
    private static final VoterIDValidator validator = new VoterIDValidator();

    public VoterID {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Voter ID: " + value);
        }
    }

    @JsonCreator
    public static VoterID fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson(){
        return value;
    }

    public static VoterID of(String value) {
        return new VoterID(value);
    }

    public static VoterID ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new VoterID(value);
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
