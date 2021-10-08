package com.example.di;

import java.util.Random;

public class NumberIdGenerator implements IDGenerator<Integer> {

    @Override
    public Integer generate() {
        Random rand = new Random();
        return rand.nextInt(1000);
    }
}
