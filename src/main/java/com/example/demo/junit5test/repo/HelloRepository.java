package com.example.demo.junit5test.repo;

import reactor.core.publisher.Mono;

public interface HelloRepository {
    public String get();
    public Mono<String> findData(Long id);
}
