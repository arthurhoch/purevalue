package io.github.arthurhoch.purevalue.internet.ip;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Validator for IPv6 addresses.
 * Uses java.net.InetAddress for validation.
 */
public final class IPv6AddressValidator implements ValueValidator {

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        try {
            InetAddress addr = InetAddress.getByName(value.trim());
            return addr instanceof java.net.Inet6Address;
        } catch (UnknownHostException e) {
            return false;
        }
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }

    public String clean(String value) {
        return (value == null) ? null : value.trim();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid IPv6 Address: " + raw);
        }
        return cleaned;
    }
}
