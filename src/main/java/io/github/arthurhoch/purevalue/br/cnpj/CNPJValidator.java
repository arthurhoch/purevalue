package io.github.arthurhoch.purevalue.br.cnpj;

import io.github.arthurhoch.purevalue.core.ValueValidator;

import java.util.Set;

public class CNPJValidator implements ValueValidator {

    private static final Set<String> INVALID_KNOWN = Set.of(
            "00000000000000", "11111111111111", "22222222222222"
    );

    @Override
    public boolean isValid(String cnpj) {
        if (cnpj == null) return false;
        cnpj = clean(cnpj);
        if (cnpj.length() != 14 || INVALID_KNOWN.contains(cnpj)) return false;

        try {
            int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

            int sum1 = 0;
            for (int i = 0; i < 12; i++) sum1 += (cnpj.charAt(i) - '0') * weights1[i];
            int check1 = sum1 % 11 < 2 ? 0 : 11 - sum1 % 11;

            int sum2 = 0;
            for (int i = 0; i < 13; i++) sum2 += (cnpj.charAt(i) - '0') * weights2[i];
            int check2 = sum2 % 11 < 2 ? 0 : 11 - sum2 % 11;

            return check1 == (cnpj.charAt(12) - '0') && check2 == (cnpj.charAt(13) - '0');
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFormatted(String value) {
        return value != null && value.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}");
    }

    public String clean(String value) {
        return value == null ? null : value.replaceAll("\\D", "");
    }

    public String format(String raw) {
        raw = clean(raw);
        return "%s.%s.%s/%s-%s".formatted(
                raw.substring(0, 2),
                raw.substring(2, 5),
                raw.substring(5, 8),
                raw.substring(8, 12),
                raw.substring(12, 14)
        );
    }
}
