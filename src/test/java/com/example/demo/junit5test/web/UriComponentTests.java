package com.example.demo.junit5test.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;

public class UriComponentTests {
    @Test
    void testQueryParam(){
        String url = UriComponentsBuilder.fromHttpUrl("https://127.0.0.1:8080/abc")
                .queryParam("p1", 123)
                .queryParam("p2", "hello world")
                .toUriString();
        System.out.println(url);
        Assertions.assertEquals("https://127.0.0.1:8080/abc?p1=123&p2=hello%20world", url);
    }
}
