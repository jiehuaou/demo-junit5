package com.example.demo.junit5test.controller;


import com.example.demo.junit5test.repo.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MyController {

    @Autowired
    HelloRepository helloRepository;

    @GetMapping("/ping")
    public Mono<String> getPing(){
        return Mono.just("hello");
    }

    @GetMapping("/data/{id}")
    public Mono<String> getData(@PathVariable("id") Long id){
        return helloRepository.findData(id)
                .switchIfEmpty(Mono.just("not-found"));
    }

    @GetMapping("/data2/{id}")
    public Mono<ResponseEntity<String>> getData2(@PathVariable("id") Long id){

        return helloRepository.findData(id)
                .map(x-> ResponseEntity.ok(x))
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest().body("not-found")));

    }

}
