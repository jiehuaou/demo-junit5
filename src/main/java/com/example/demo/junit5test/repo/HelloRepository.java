package com.example.demo.junit5test.repo;

import reactor.core.publisher.Mono;

public interface HelloRepository {
     String get();
     String getData(Long id);
     Mono<String> findData(Long id);
     void create(String data);
}
