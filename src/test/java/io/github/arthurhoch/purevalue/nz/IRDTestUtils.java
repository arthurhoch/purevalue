import java.util.Random;

public class IRDTestUtils {

    private static final int[] PRIMARY_WEIGHTS = {3, 2, 7, 6, 5, 4, 3};
    private static final int[] ALT_WEIGHTS = {7, 4, 3, 2, 5, 2, 7};

    public static void main(String[] args) {
        System.out.println("IRD Numbers válidos de 8 dígitos:");
        for (int i = 0; i < 10; i++) {
            String ird = generateValidIRD8Digits();
            System.out.println(ird);
        }
    }

    public static String generateValidIRD8Digits() {
        Random r = new Random();
        while (true) {
            int[] digits = new int[7];

            // Gera primeiro dígito de 1 a 9 para evitar zero à esquerda
            digits[0] = r.nextInt(9) + 1;

            for (int i = 1; i < 7; i++) digits[i] = r.nextInt(10);

            int checkDigit = computeCheckDigit(digits, PRIMARY_WEIGHTS);
            if (checkDigit == 10) checkDigit = computeCheckDigit(digits, ALT_WEIGHTS);
            if (checkDigit == 10) continue;

            StringBuilder sb = new StringBuilder();
            for (int d : digits) sb.append(d);
            sb.append(checkDigit);

            return sb.toString(); // 8 dígitos, sem prefixo 0
        }
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
