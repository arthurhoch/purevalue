package io.github.arthurhoch.purevalue.br.state;

import io.github.arthurhoch.purevalue.core.ValueValidator;

public class StateRegistrationBrazilValidator implements ValueValidator {
    @Override
    public boolean isValid(String value) {
        if (value == null) return false;
        value = clean(value);
        return value.matches("\\d{9,14}");
    }

    public boolean isFormatted(String value) {
        return value != null && value.matches("\\d{9,14}");
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        return raw;
    }
}
