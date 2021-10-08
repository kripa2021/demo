package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringUtilTest {

    @Test
    void toUpperCase() {
        String testInput = "testing";

        String actualVal = StringUtil.toUpperCase(testInput);

        Assertions.assertEquals("TESTING", actualVal);
    }
}