package io.github.arthurhoch.purevalue.internet.bt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Bluetooth Address.
 */
public record BluetoothAddress(String value) implements PureValue<String> {
    private static final BluetoothAddressValidator validator = new BluetoothAddressValidator();

    public BluetoothAddress {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Bluetooth Address: " + value);
        }
    }

    @JsonCreator
    public static BluetoothAddress fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static BluetoothAddress of(String value) {
        return new BluetoothAddress(value);
    }

    public static BluetoothAddress ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new BluetoothAddress(value);
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
