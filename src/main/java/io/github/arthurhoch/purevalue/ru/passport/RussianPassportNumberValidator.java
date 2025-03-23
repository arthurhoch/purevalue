// File: RussianPassportNumberValidator.java
package io.github.arthurhoch.purevalue.ru.passport;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Russian Passport Number.
 * Accepts either exactly 9 digits or exactly 10 digits.
 */
public final class RussianPassportNumberValidator implements ValueValidator {

    private static final Pattern PATTERN_9 = Pattern.compile("\\d{9}");
    private static final Pattern PATTERN_10 = Pattern.compile("\\d{10}");

    @Override
    public boolean isValid(String value) {
        if(value == null) return false;
        String cleaned = clean(value);
        return PATTERN_9.matcher(cleaned).matches() || PATTERN_10.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        return value != null && isValid(clean(value));
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if(cleaned == null || !(PATTERN_9.matcher(cleaned).matches() || PATTERN_10.matcher(cleaned).matches())) {
            throw new IllegalArgumentException("Invalid Russian Passport Number: " + raw);
        }
        return cleaned;
    }
}
