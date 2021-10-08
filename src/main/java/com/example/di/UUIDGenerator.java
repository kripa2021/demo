package com.example.di;

import java.util.UUID;

public class UUIDGenerator implements IDGenerator<String> {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }

}
