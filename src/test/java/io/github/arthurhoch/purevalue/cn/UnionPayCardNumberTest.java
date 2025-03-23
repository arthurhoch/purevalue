package io.github.arthurhoch.purevalue.cn;

import io.github.arthurhoch.purevalue.cn.unionpay.UnionPayCardNumber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for UnionPayCardNumber.
 */
class UnionPayCardNumberTest {

    @Test
    void shouldAcceptValidUnionPayCardNumber16Digits() {
        // Example valid 16-digit UnionPay card number (assumed valid)
        UnionPayCardNumber card = UnionPayCardNumber.of("6212345678901232");
        assertEquals("6212345678901232", card.value());
    }

    @Test
    void shouldAcceptValidUnionPayCardNumber19Digits() {
        // For the candidate 18-digit prefix "622588012345678901",
        // the Luhn algorithm computes the check digit as 7.
        // Thus, a valid 19-digit UnionPay number is "6225880123456789017".
        UnionPayCardNumber card = UnionPayCardNumber.of("6225880123456789017");
        assertEquals("6225880123456789017", card.value());
    }

    @Test
    void shouldRejectInvalidUnionPayCardNumber() {
        // Wrong check digit: change the last digit
        assertThrows(IllegalArgumentException.class, () -> UnionPayCardNumber.of("6212345678901231"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("6212345678901232", UnionPayCardNumber.of("62 1234 5678 9012 32").format());
    }
}
