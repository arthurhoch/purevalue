// File: TaxIDGermany.java
package io.github.arthurhoch.purevalue.de.taxid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a German Tax Identification Number.
 */
public record TaxIDGermany(String value) implements PureValue<String> {
    private static final TaxIDGermanyValidator validator = new TaxIDGermanyValidator();

    public TaxIDGermany {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid German Tax ID: " + value);
        }
    }

    @JsonCreator
    public static TaxIDGermany fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static TaxIDGermany of(String value) {
        return new TaxIDGermany(value);
    }

    public static TaxIDGermany ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new TaxIDGermany(value);
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

    @Override
    public String toString() {
        return value;
    }
}
