// File: EuropeanLicensePlate.java
package io.github.arthurhoch.purevalue.eu.licenseplate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a European License Plate.
 */
public record EuropeanLicensePlate(String value) implements PureValue<String> {
    private static final EuropeanLicensePlateValidator validator = new EuropeanLicensePlateValidator();

    public EuropeanLicensePlate {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid European License Plate: " + value);
        }
    }

    @JsonCreator
    public static EuropeanLicensePlate fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static EuropeanLicensePlate of(String value) {
        return new EuropeanLicensePlate(value);
    }

    public static EuropeanLicensePlate ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new EuropeanLicensePlate(value);
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
