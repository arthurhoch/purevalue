package io.github.arthurhoch.purevalue.it.partitaiva;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing Italian Partita IVA.
 */
public record PartitaIVA(String value) implements PureValue<String> {
    private static final PartitaIVAValidator validator = new PartitaIVAValidator();

    public PartitaIVA {
        value = clean(value);
        if(!isValid(value)){
            throw new IllegalArgumentException("Invalid Partita IVA: " + value);
        }
    }

    @JsonCreator
    public static PartitaIVA fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static PartitaIVA of(String value) {
        return new PartitaIVA(value);
    }

    public static PartitaIVA ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new PartitaIVA(value);
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
