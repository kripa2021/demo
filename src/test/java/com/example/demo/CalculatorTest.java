package com.example.demo;

import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Single responsibility principle
class CalculatorTest {
    int val = 3;

    @BeforeAll
    static void init() {
        System.out.println("BEFORE ALL");
    }

    @BeforeEach
    public void init1() {
        System.out.println("BEFORE EACH");
    }

    @Test
    void add() {
        System.out.println(UUID.randomUUID().toString());
        int a = 3, b = 5;
        int sum = Calculator.add(a, b);

        Assertions.assertEquals(8,  sum);
        org.assertj.core.api.Assertions.assertThat(sum)
            .isEqualTo(8);
    }

    @Test
    void subtract() {
        int valToSubtract = 40;
        int initialValue = Calculator.val;
        Calculator.subtract(valToSubtract);

        org.assertj.core.api.Assertions.assertThat(Calculator.val)
            .isEqualTo(initialValue - valToSubtract);
    }
}
