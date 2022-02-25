package com.example.demo.junit5test.conditional;


import org.springframework.stereotype.Component;

@Component
public class MyWorld implements SubRepository {
    @Override
    public String world() {
        return "world";
    }
}
