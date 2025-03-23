package io.github.arthurhoch.purevalue.br.cpf;

import io.github.arthurhoch.purevalue.core.ValueValidator;

import java.util.Set;

public class CPFValidator implements ValueValidator {

    private static final Set<String> INVALID_KNOWN = Set.of(
            "00000000000", "11111111111", "22222222222", "33333333333",
            "44444444444", "55555555555", "66666666666", "77777777777",
            "88888888888", "99999999999", "12345678909"
    );

    @Override
    public boolean isValid(String cpf) {
        if (cpf == null) return false;

        cpf = clean(cpf);

        if (cpf.length() != 11 || INVALID_KNOWN.contains(cpf)) return false;

        try {
            int sum1 = 0, sum2 = 0;
            for (int i = 0; i < 9; i++) {
                int digit = cpf.charAt(i) - '0';
                sum1 += digit * (10 - i);
                sum2 += digit * (11 - i);
            }

            int check1 = (sum1 * 10) % 11;
            check1 = (check1 == 10) ? 0 : check1;
            sum2 += check1 * 2;

            int check2 = (sum2 * 10) % 11;
            check2 = (check2 == 10) ? 0 : check2;

            return check1 == cpf.charAt(9) - '0' && check2 == cpf.charAt(10) - '0';
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFormatted(String value) {
        return value != null && value.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        raw = clean(raw);
        return "%s.%s.%s-%s".formatted(
                raw.substring(0, 3),
                raw.substring(3, 6),
                raw.substring(6, 9),
                raw.substring(9, 11)
        );
    }
}
