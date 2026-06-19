package com.example.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PaymentServiceAppTest {
    @Test
    void chargesPositiveAmount() {
        assertEquals("{\"paymentId\":\"pay-100\",\"amount\":10}", PaymentServiceApp.charge(10));
    }

    @Test
    void rejectsZeroAmount() {
        assertThrows(IllegalArgumentException.class, () -> PaymentServiceApp.charge(0));
    }

    @Test
    void rejectsNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> PaymentServiceApp.charge(-1));
    }
}
