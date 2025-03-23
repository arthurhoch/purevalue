package io.github.arthurhoch.purevalue.crypto.lightning;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.arthurhoch.purevalue.core.PureValue;

/**
 * Record representing a Bitcoin Lightning Invoice.
 */
public record LightningInvoice(String value) implements PureValue<String> {
    private static final LightningInvoiceValidator validator = new LightningInvoiceValidator();

    public LightningInvoice {
        value = clean(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Lightning Invoice: " + value);
        }
    }

    @JsonCreator
    public static LightningInvoice fromJson(String value) {
        return ofNullable(value);
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    public static LightningInvoice of(String value) {
        return new LightningInvoice(value);
    }

    public static LightningInvoice ofNullable(String value) {
        return (value == null || value.isBlank()) ? null : new LightningInvoice(value);
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
