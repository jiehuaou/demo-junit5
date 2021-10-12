package com.example.demo.junit5test.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/ping")
    public String getPing(){
        return "hello";
    }
}
