package com.example.demo.junit5test;

import com.example.demo.junit5test.logic.MyValidator;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MyConfig {
    @Bean
    public MyValidator getValidator(){
        return new MyValidator();
    }
}
