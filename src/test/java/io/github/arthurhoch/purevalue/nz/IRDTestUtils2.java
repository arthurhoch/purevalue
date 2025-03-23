package io.github.arthurhoch.purevalue.nz;

import java.util.Random;

public class IRDTestUtils2 {

    private static final int[] PRIMARY_WEIGHTS = {3, 2, 7, 6, 5, 4, 3};
    private static final int[] ALT_WEIGHTS = {7, 4, 3, 2, 5, 2, 7};

    public static void main(String[] args) {
        System.out.println("IRD Numbers válidos de 8 dígitos:");
        for (int i = 0; i < 10; i++) {
            System.out.println(generateValidIRD8Digits());
        }

        System.out.println("\nIRD Numbers válidos de 9 dígitos:");
        for (int i = 0; i < 10; i++) {
            System.out.println(generateValidIRD9Digits());
        }
    }

    public static String generateValidIRD8Digits() {
        Random r = new Random();
        while (true) {
            int[] digits = new int[7];
            digits[0] = r.nextInt(9) + 1; // evita começar com 0
            for (int i = 1; i < 7; i++) {
                digits[i] = r.nextInt(10);
            }

            int checkDigit = computeCheckDigit(digits, PRIMARY_WEIGHTS);
            if (checkDigit == 10) checkDigit = computeCheckDigit(digits, ALT_WEIGHTS);
            if (checkDigit == 10) continue;

            StringBuilder sb = new StringBuilder();
            for (int d : digits) sb.append(d);
            sb.append(checkDigit);

            return sb.toString(); // 8 dígitos válidos
        }
    }

    public static String generateValidIRD9Digits() {
        return "0" + generateValidIRD8Digits(); // só prefixa com zero
    }

    private static int computeCheckDigit(int[] digits, int[] weights) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += digits[i] * weights[i];
        }
        int mod = sum % 11;
        return (mod == 0) ? 0 : 11 - mod;
    }
}
