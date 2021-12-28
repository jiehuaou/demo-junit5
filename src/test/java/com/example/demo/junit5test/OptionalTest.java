package com.example.demo.junit5test;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalTest {
    @Test
    void testMapElse1(){
        Optional<String> data = Optional.of("hello");
        String out = data.map(s->s+"-001").orElse("world");
        Assertions.assertEquals("hello-001", out);
    }

    @Test
    void testMapElse2(){
        Optional<String> data = Optional.empty();
        String out = data.map(String::toUpperCase).orElse("world");
        Assertions.assertEquals("world", out);
    }

    @Test
    void testFilterElse(){
        Optional<String> data = Optional.of("Hello");
        String out = data
                .filter(s->s.equalsIgnoreCase("hello-1"))
                .orElse("world");
        Assertions.assertEquals("world", out);
    }

    Optional<String> getOptional(String text){
        if("empty".equalsIgnoreCase(text)){
            return Optional.empty();
        }
        return Optional.of(text);
    }

    @Test
    void testOptionalEqual(){
        // test equal
        Optional<String> actual = getOptional("hello");

        Assertions.assertEquals(Optional.of("hello"), actual);

        // test not equal
        Optional<String> empty = getOptional("empty");
        Assertions.assertTrue(empty.isEmpty());
        Assertions.assertNotEquals(Optional.of("hello"), empty);


    }
}
