// File: CURPMexicoValidator.java
package io.github.arthurhoch.purevalue.mx.curp;

import io.github.arthurhoch.purevalue.core.ValueValidator;
import java.util.regex.Pattern;

/**
 * Validator for Mexican CURP (Clave Única de Registro de Población).
 * Expects 18 characters with the following structure:
 * - 4 letters,
 * - 6 digits (birth date in YYMMDD),
 * - 1 letter (gender: H or M),
 * - 2 letters (state code, one of the official codes),
 * - 3 letters,
 * - 1 alphanumeric (homoclave),
 * - 1 digit (check digit).
 */
public final class CURPMexicoValidator implements ValueValidator {

    private static final Pattern RAW_PATTERN = Pattern.compile(
            "^[A-Z]{4}\\d{6}[HM](AS|BC|BS|CC|CL|CM|CS|CH|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS)[A-Z]{3}[0-9A-Z]\\d$"
    );

    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        String cleaned = clean(value);
        return RAW_PATTERN.matcher(cleaned).matches();
    }

    public boolean isFormatted(String value) {
        return isValid(value);
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\s", "").toUpperCase();
    }

    public String format(String raw) {
        String cleaned = clean(raw);
        if (cleaned == null || !isValid(cleaned)) {
            throw new IllegalArgumentException("Invalid CURP: " + raw);
        }
        return cleaned;
    }
}
