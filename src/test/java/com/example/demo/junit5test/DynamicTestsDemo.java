package com.example.demo.junit5test;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * Dynamic Test
 */

@Log4j2
public class DynamicTestsDemo {
    private final AssertionsDemo.Calculator calculator = new AssertionsDemo.Calculator();

    @TestFactory
    Collection<DynamicTest> dynamicTestsFromCollection() {
        return Arrays.asList(
                dynamicTest("1st dynamic test", () -> assertEquals(3,  calculator.add(1, 2))),
                dynamicTest("2nd dynamic test", () -> assertEquals(4, calculator.multiply(2, 2)))
        );
    }
}
