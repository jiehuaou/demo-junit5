package com.example.demo.junit5test;

import lombok.Data;
//import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.condition.OS.LINUX;
import static org.junit.jupiter.api.condition.OS.MAC;

/**
 * Assertions method demo
 */
@Log4j2
public class AssertionsDemo {

    @Data
    public static class Person {
        final private String firstName;
        final private String lastName;
        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    public static class Calculator{
        int add (int x1, int x2){
            return x1+x2;
        }
        int multiply (int x1, int x2){
            return x1 * x2;
        }

        public int divide(int x1, int x2) {
//            float f1 = x1;
            return x1/x2;
        }
    }

    private final Calculator calculator = new Calculator();
    private final Person person = new Person("Jane", "Doe");

    @Test
    void standardAssertions() {
        assertEquals(2, calculator.add(1, 1));
        assertEquals(4, calculator.multiply(2, 2),
                "The optional failure message is now the last parameter");
        assertTrue('a' < 'b', () -> "Assertion messages can be lazily evaluated -- "
                + "to avoid constructing complex messages unnecessarily.");
    }

    @Test
    void groupedAssertions() {
        // In a grouped assertion all assertions are executed, and all
        // failures will be reported together.
        assertAll("person",
                () -> assertEquals("Jane", person.getFirstName()),
                () -> assertEquals("Doe", person.getLastName())
        );
    }

    @Test
    void dependentAssertions() {
        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.
        assertAll("properties",
                () -> {
                    log.info("dependentAssertions-1a");
                    String firstName = person.getFirstName();
                    assertNotNull(firstName);
                    // assertNull(firstName);  // will skip following assert

                    log.info("dependentAssertions-1b");
                    // Executed only if the previous assertion is valid.
                    assertAll("first name",
                            () -> assertTrue(firstName.startsWith("J")),
                            () -> assertTrue(firstName.endsWith("e"))
                    );
                },
                () -> {
                    // Grouped assertion, so processed independently
                    // of results of first name assertions.
                    log.info("dependentAssertions-2a");
                    String lastName = person.getLastName();
                    assertNotNull(lastName);

                    // Executed only if the previous assertion is valid.
                    log.info("dependentAssertions-2b");
                    assertAll("last name",
                            () -> assertTrue(lastName.startsWith("D")),
                            () -> assertTrue(lastName.endsWith("e"))
                    );
                }
        );
    }

    @Test
    void exceptionTesting() {

        Exception exception = assertThrows(ArithmeticException.class, () ->{
                    int ret = calculator.divide(1, 0);
                    log.info("calculator.divide(1, 0) ===> {}", ret);
                });
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    void timeoutNotExceeded() {
        // The following assertion fails with an error message similar to:
        // execution not exceeded timeout of 200 ms
        assertTimeout(Duration.ofMillis(200), () -> {
            // Simulate task that takes more than 10 ms.
            log.info("sleep 100 milliseconds");
            Thread.sleep(100);
        });
    }

    @Disabled("Disabled until bug #42 has been resolved")
    @Test
    void testWillBeSkipped() {
        log.info("sleep 100 milliseconds");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @EnabledOnOs({ LINUX, MAC })
    void onLinuxOrMac() {
        log.info("test on LINUX, MAC");
    }

//    @Value("${os.arch}")
//    private String osArch;
    //  JVM system property
    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    void onlyOn64BitArchitectures() {
        //log.info("test on 64 bit cpu -> {}", osArch);
        log.info("test on 64 bit cpu -> {}", System.getProperty("os.arch"));
    }

    //  Custom Conditions
    @Test
    @EnabledIf("customCondition")
    void testByCustomCondition() {
        log.info("test on customCondition -> {}", customCondition());
    }

    boolean customCondition() {
        return true;
    }

}
