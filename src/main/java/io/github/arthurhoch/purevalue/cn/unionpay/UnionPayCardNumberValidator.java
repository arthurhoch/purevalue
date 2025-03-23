package io.github.arthurhoch.purevalue.cn.unionpay;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for UnionPay Card Number.
 * Typically, UnionPay cards start with "62" and are either 16 or 19 digits.
 * Uses the Luhn algorithm for validation.
 */
public final class UnionPayCardNumberValidator implements ValueValidator {
    private static final Pattern RAW_PATTERN = Pattern.compile("^62\\d{14}(\\d{3})?$");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;
        return luhnCheck(cleaned);
    }

    private boolean luhnCheck(String number) {
        int sum = 0;
        boolean alternate = false;
        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) digit -= 9;
            }
            sum += digit;
            alternate = !alternate;
        }
        return sum % 10 == 0;
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }

    public String clean(String value) {
        return (value == null) ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid UnionPay Card Number: " + raw);
        }
        return cleaned;
    }
}
