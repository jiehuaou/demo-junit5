package com.example.demo.junit5test.controller;

import com.example.demo.junit5test.svc.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello/{id}")
    public String getHello(@PathVariable("id") String id){
        return helloService.greet(id);
    }
}
