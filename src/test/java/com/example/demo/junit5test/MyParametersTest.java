package com.example.demo.junit5test;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;
//import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * test case with Parameters
 */
@Log4j2
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MyRandomParametersExtension.class)
public class MyParametersTest {

    @BeforeAll
    void beforeAllTests() {
        log.info("Before all tests");
    }

    @AfterAll
     void afterAllTests() {
        log.info("After all tests");
    }

    @BeforeEach
     void beforeEachTest(TestInfo testInfo) {
        log.info(() -> String.format("About to execute [%s]",
                testInfo.getDisplayName()));
    }

    @AfterEach
     void afterEachTest(TestInfo testInfo) {
        log.info(() -> String.format("Finished executing [%s]",
                testInfo.getDisplayName()));
    }

    @Test
    @RepeatedTest(3)
    //@DisplayName("test 2 MyRandom parameters")
    void injectsInteger(@MyRandom int i, @MyRandom int j) {
        log.info("test 2 int MyRandom parameters {} - {}", i, j);
        assertNotEquals(i, j);
    }

    @Test
    @DisplayName("test 1 MyRandom parameters <= 1.5")
    void injectsDouble(@MyRandom double d) {
        log.info("test 1 double MyRandom parameters {} ", d);
        assertEquals(0.0, d , 1.5);  // abs(value1 - value2) <= delta
    }

    @ParameterizedTest
    @ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
    void palindromes(String candidate) {
        log.info(candidate);
    }

    // method source
    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithExplicitLocalMethodSource(String argument) {
        log.info(argument);
    }

    static Stream<String> stringProvider() {
        return Stream.of("apple", "banana");
    }

    // method source with multi arg
    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int num, List<String> list) {
        log.info("arg1={},  arg2={},  arg3={}", str, num, list);
    }

    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                Arguments.arguments("apple", 1, Arrays.asList("a", "b")),
                Arguments.arguments("lemon", 2, Arrays.asList("x", "y"))
        );
    }

    // method source with multi arg from other class provider
    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    void testWithArgumentsSource(String str, int num, List<String> list) {
        log.info("arg1={},  arg2={},  arg3={}", str, num, list);
    }
}
