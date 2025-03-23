// File: NIRValidator.java
package io.github.arthurhoch.purevalue.fr.nir;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.math.BigInteger;
import java.util.regex.Pattern;

/**
 * Validator for French NIR (Numéro de Sécurité Sociale).
 * Expects a 15-digit number where the first 13 digits form the base and the last 2 are the control key.
 * The control key is computed as: 97 - (base mod 97).
 */
public final class NIRValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile("\\d{15}");

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        if (!RAW_PATTERN.matcher(cleaned).matches()) return false;

        String base = cleaned.substring(0, 13);
        String keyStr = cleaned.substring(13);
        BigInteger baseNum = new BigInteger(base);
        int remainder = baseNum.mod(BigInteger.valueOf(97)).intValue();
        int expectedKey = 97 - remainder;
        int providedKey = Integer.parseInt(keyStr);
        return expectedKey == providedKey;
    }

    public boolean isFormatted(String value) {
        return value != null && RAW_PATTERN.matcher(clean(value)).matches();
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || cleaned.length() != 15 || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid NIR: " + raw);
        }
        return cleaned;
    }
}
